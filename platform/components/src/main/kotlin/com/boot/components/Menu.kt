package com.boot.components

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boot.components.gesture.pressGesture
import com.boot.designsystem.theme.material.AppMaterialTheme

@Composable
fun MenuToClose(
	isShow: Boolean,
	onClick: () -> Unit,
	lineColor: Color,
	initialHeight: Dp,
	canvasWidth: Dp,
	strokeTickness: Dp
) {
	val density = LocalDensity.current
	val endY = with(density) { initialHeight.roundToPx().toFloat() }
	val strokeWidth = with(density) { (strokeTickness).roundToPx().toFloat() }

	@Composable
	fun springSpec(visibilityThreshold: Float? = null): SpringSpec<Float> =
		spring(0.4f, 500f, visibilityThreshold)

	val lineOneEndY by
	animateFloatAsState(
		targetValue = if (isShow) 0f else endY,
		animationSpec = springSpec(),
	)
	val lineTwoAlpha by
	animateFloatAsState(
		targetValue = if (isShow) 1f else 0f,
		animationSpec = springSpec(0f),
	)
	val lineTwoScale by
	animateFloatAsState(
		targetValue = if (isShow) 1f else 0f,
		animationSpec = springSpec(),
	)
	val lineThreeEndY by
	animateFloatAsState(
		targetValue = if (isShow) endY else 0f,
		animationSpec = springSpec(),
	)

	Canvas(
		Modifier
			.size(height = initialHeight, width = canvasWidth)
			.pressGesture(onClick = onClick),
	) {
		val (width, height) = size

		fun line(start: Offset, end: Offset, alpha: Float = 1f) {
			drawLine(
				color = lineColor,
				strokeWidth = strokeWidth,
				cap = StrokeCap.Round,
				start = start,
				end = end,
				alpha = alpha,
			)
		}
		line(
			start = Offset(x = 0f, y = 0f),
			end = Offset(x = width, y = lineOneEndY),
		)
		withTransform({ scale(lineTwoScale, Offset(x = 0f, y = height / 2)) }) {
			line(
				start = Offset(x = 0f, y = height / 2),
				end = Offset(x = width, y = height / 2),
				alpha = lineTwoAlpha,
			)
		}
		line(
			start = Offset(x = 0f, y = height),
			end = Offset(x = width, y = lineThreeEndY),
		)
	}
}

@Preview(name = "MenuToClose")
@Composable
fun MenuToClosePreview() {
	var isShow by remember { mutableStateOf(true) }
	AppMaterialTheme {
		Column(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement =
			Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Text(text = "isShow: $isShow")
			MenuToClose(
				isShow = isShow,
				onClick = { isShow = !isShow },
				lineColor = MaterialTheme.colorScheme.onBackground,
				initialHeight = 16.dp,
				canvasWidth = 20.dp,
				strokeTickness = 4.dp,
			)
		}
	}
}
