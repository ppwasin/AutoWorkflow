package com.boot.components.search.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.boot.components.search.item.Tag.EndLayoutId
import com.boot.components.search.item.Tag.MiddleLayoutId
import com.boot.components.search.item.Tag.StartLayoutId
import com.boot.components.utils.constraintVertical
import com.boot.designsystem.theme.material.AppMaterialTheme

private enum class Tag {
	StartLayoutId,
	MiddleLayoutId,
	EndLayoutId
}

@Composable
fun SearchItemSlot(
	startIcon: @Composable () -> Unit,
	text: @Composable () -> Unit,
	endIcon: @Composable (() -> Unit)? = null
) {
	val iconSize = ButtonDefaults.IconSize
	val iconSpace = ButtonDefaults.IconSpacing

	ConstraintLayout(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		constraintSet =
		ConstraintSet {
			val startConst = createRefFor(StartLayoutId)
			val middleConst = createRefFor(MiddleLayoutId)
			val endConst = createRefFor(EndLayoutId)

			constrain(startConst) {
				constraintVertical()
				start.linkTo(parent.start)
			}
			constrain(middleConst) {
				constraintVertical()
				start.linkTo(startConst.end, margin = iconSpace)
				if (endIcon != null) {
					end.linkTo(endConst.start, margin = iconSpace)
				} else {
					end.linkTo(parent.end, margin = iconSpace)
				}
				width = Dimension.fillToConstraints
			}
			constrain(endConst) {
				constraintVertical()
				end.linkTo(parent.end)
			}
		},
	) {
		Box(
			Modifier
				.layoutId(StartLayoutId)
				.size(iconSize)) { startIcon() }
		Box(modifier = Modifier.layoutId(MiddleLayoutId)) { text() }
		if (endIcon != null)
			Box(modifier = Modifier
				.layoutId(EndLayoutId)
				.size(iconSize)) {
				endIcon()
			}
	}
}

@ExperimentalComposeUiApi
@Preview(device = Devices.PIXEL_2_XL, showBackground = true)
@Composable
fun SearchItemSlotPreview(
	@PreviewParameter(SearchItemPreviewProvider::class)
	data: SearchItemPreviewProvider.Data
) {
	AppMaterialTheme {
		SearchItemSlot(
			startIcon = {
				Icon(
					Icons.Default.AccessAlarm,
					contentDescription = "SearchStartIcon",
				)
			},
			text = { SearchTextRow(title = data.title, subTitle = data.subTitle) },
			endIcon = data.endIcon,
		)
	}
}
