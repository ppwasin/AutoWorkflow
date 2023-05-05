package com.boot.components.gesture

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview

enum class ButtonState { Pressed, Idle }

fun Modifier.bounceClick(
	scaleDownSize: Float = 0.90f,
	onClick: () -> Unit
) = composed {
	var buttonState by remember { mutableStateOf(ButtonState.Idle) }
	val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) scaleDownSize else 1f)

	this
		.graphicsLayer {
			scaleX = scale
			scaleY = scale
		}
		.clickable(
			interactionSource = remember { MutableInteractionSource() },
			indication = null,
			onClick = onClick,
		)
		.pointerInput(buttonState) {
			awaitPointerEventScope {
				buttonState = if (buttonState == ButtonState.Pressed) {
					waitForUpOrCancellation()
					ButtonState.Idle
				} else {
					awaitFirstDown(false)
					ButtonState.Pressed
				}
			}
		}
}


@Preview
@Composable
fun BounceClickPreview() {
	Button(
		modifier = Modifier.bounceClick {},
		onClick = { },
	) {
		Text("Click Me")
	}
}
