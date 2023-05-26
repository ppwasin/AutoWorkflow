@file:OptIn(ExperimentalAnimationApi::class)

package com.boot.playground.animation.transition.lookahead

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentWithReceiverOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.boot.components.gesture.bounceClick
import com.boot.designSystem.R
import com.boot.designsystem.theme.material.AppMaterialTheme
import com.boot.playground.animation.transition.Notification

class ContainerViewModel : ViewModel() {
	var selectedItem by mutableStateOf<Int?>(null)
		private set

	fun navigate(itemId: Int) {
		selectedItem = itemId
	}

	fun pop() {
		selectedItem = null
	}
}

@Composable
fun ContainerTransformList(viewModel: ContainerViewModel = viewModel()) {
//	val scrollState = rememberLazyListState()
	val scrollState = rememberScrollState()
	val notifications = remember {
		Notification.fakeList().map { value ->
			SharedElement(
				id = value.id,
				content = movableContentWithReceiverOf { modifier, isCollapse ->
					Card(
						Modifier
							.fillMaxWidth()
							.wrapContentHeight()
							.conditionallyAnimateBounds(!scrollState.isScrollInProgress)
							.bounceClick { viewModel.navigate(value.id) }
					) {
						Column(Modifier.padding(8.dp)) {
							NotificationHeader(
								modifier = Modifier,
								notification = value,
							)
							Spacer(modifier = Modifier.size(8.dp))
							Text("isCollapse: $isCollapse")
							Column {
								if (isCollapse) {
									Text(
										modifier = Modifier,
										text = value.description,
										maxLines = 2,
										overflow = TextOverflow.Ellipsis,
									)
								} else {
									Text(
										modifier = Modifier,
										text = value.description,
									)
								}
							}
						}
					}

				},
			)
		}
	}

	BackHandler(
		enabled = true,
		onBack = {
			viewModel.pop()
		},
	)

	Scaffold(
		modifier = Modifier.padding(8.dp),
		floatingActionButton = {
			if (viewModel.selectedItem != null) {
				Button(onClick = { viewModel.pop() }) {
					Text("Go Back")
				}
			}

		},
	) { paddingValue ->
		LookaheadScope {
			if (viewModel.selectedItem == null) {
//				LazyColumn(
//					modifier = Modifier.padding(paddingValue),
//					verticalArrangement = Arrangement.spacedBy(16.dp),
//					state = scrollState
//				) {
//					items(notifications) {
//						it.content(this@LookaheadScope, true)
//					}
//				}

				Column(
					modifier = Modifier
						.padding(paddingValue)
						.verticalScroll(scrollState),
					verticalArrangement = Arrangement.spacedBy(16.dp),
				) {
					notifications.forEach {
						it.content(this@LookaheadScope, Modifier, true)
					}
				}
			} else {
				Column(Modifier.fillMaxSize()) {
					notifications.find { it.id == viewModel.selectedItem }?.let {
						it.content(this@LookaheadScope, Modifier, false)
					}
				}

				ConstraintLayout(Modifier.fillMaxSize()) {
					val (content, menuBar) = createRefs()
					notifications.find { it.id == viewModel.selectedItem }?.let {
						it.content(
							this@LookaheadScope,
							Modifier.constrainAs(content) {
								top.linkTo(parent.top)
								start.linkTo(parent.start)
								end.linkTo(parent.end)
							},
							false,
						)
						Text(
							modifier = Modifier.constrainAs(menuBar) {
								bottom.linkTo(parent.bottom)
							},
							text = "Test",
						)
					}
				}

			}

		}
	}
}

context(LookaheadScope)
	@Composable
	private fun NotificationHeader(modifier: Modifier = Modifier, notification: Notification) {
	Row(modifier) {
		Image(
			modifier = Modifier
				.size(32.dp)
				.clip(CircleShape),
			painter = rememberAsyncImagePainter(
				notification.img,
				placeholder = painterResource(R.drawable.ic_placeholder_24),
			),
			contentDescription = null,
			contentScale = ContentScale.Crop,
		)

		Spacer(modifier = Modifier.size(8.dp))
		Column {
			Text(modifier = Modifier, text = notification.sender)
			Spacer(modifier = Modifier.size(4.dp))
			Text(modifier = Modifier, text = notification.sendDate)
			Spacer(modifier = Modifier.size(4.dp))
			Row(modifier = Modifier) {
				Text(text = notification.type.name)
				Spacer(modifier = Modifier.size(4.dp))
				Text(text = notification.priority.name)
			}
		}
	}
}

@Composable
private fun TextExpandable(modifier: Modifier, text: String, isCollapse: Boolean){
	if (isCollapse) {
		Text(
			modifier = Modifier,
			text = text,
			maxLines = 2,
			overflow = TextOverflow.Ellipsis,
		)
	} else {
		Text(
			modifier = Modifier,
			text = text,
		)
	}
}

@Composable
private fun ItemPartialDescription(modifier: Modifier, descriptionText: String) {
	Text(modifier = modifier, text = descriptionText, maxLines = 2, overflow = TextOverflow.Ellipsis)
}

@Composable
private fun ItemFullDescription(modifier: Modifier, descriptionText: String) {
	Text(modifier = modifier, text = descriptionText)
}

private class SharedElement(
	val id: Int,
	val content: @Composable LookaheadScope.(Modifier, Boolean) -> Unit
)


@Preview
@Composable
fun ContainerTransformListPlayground() {
	AppMaterialTheme {
		ContainerTransformList()
	}
}
