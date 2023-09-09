package com.boot.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun ErrorBalloon(
    message: String,
    backgroundColor: Color = Color.White
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = message,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.error,
                    shape = BubbleShape()
                )
                .background(shape = BubbleShape(), color = backgroundColor)
                .padding(16.dp)
        )
        Image(
            imageVector = Icons.Outlined.Error,
            contentDescription = "error",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
        )
    }
}

@Composable
fun ErrorBalloonText(
    modifier: Modifier = Modifier,
    message: String,
    backgroundColor: Color = Color.White
) {
    Text(
        text = message,
        style = MaterialTheme.typography.labelSmall,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier
            .wrapContentSize()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.error,
                shape = BubbleShape()
            )
            .background(shape = BubbleShape(), color = backgroundColor)
            .padding(16.dp)
    )
}

@Preview
@Composable
fun ErrorBalloonPreview() {
    ErrorBalloon("Sample error")
}

class BubbleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val roundStart = size.height / 2
        val arrowHeight = 32F
        val arrowWidth = 64F
//        return Outline.Generic(
//            path = Path().apply {
//                arcTo(
//                    rect = Rect(
//                        left = 0f,
//                        top = 0f,
//                        right = roundStart * 2,
//                        bottom = (roundStart * 2),
//                    ),
//                    startAngleDegrees = -90f,
//                    sweepAngleDegrees = -180f,
//                    forceMoveTo = false
//                )
//                lineTo(x = size.width / 2 - arrowWidth / 2, y = size.height)
//                lineTo(x = size.width / 2, y = size.height + arrowHeight)
//                lineTo(x = size.width / 2 + arrowWidth / 2, y = size.height)
//                lineTo(x = size.width - roundStart, y = size.height)
//                arcTo(
//                    rect = Rect(
//                        left = size.width - roundStart * 2,
//                        top = 0f,
//                        right = size.width,
//                        bottom = size.height,
//                    ),
//                    startAngleDegrees = 90f,
//                    sweepAngleDegrees = -180f,
//                    forceMoveTo = false
//                )
//
//                close()
//            }
//        )

        val cornerSize = Size(32f, 32f)
        val bottomStartSize = 32f
        val height = size.height
        val width = size.width
        return Outline.Generic(
            path = Path().apply {
                arcTo(
                    rect = Rect(
                        Offset(0f,0f),
                        cornerSize
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = true
                )
                lineTo(x = size.width, y = 0f)

                arcTo(
                    rect = Rect(
                        Offset(x = size.width - cornerSize.width, y = 0f),
                        cornerSize
                    ),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(x = width, y = height - cornerSize.height)
                arcTo(
                    Rect(
                        Offset(x = size.width - cornerSize.width, y = size.height - cornerSize.height),
                        cornerSize
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(x = size.width / 2 + arrowWidth / 2, y = size.height)
                lineTo(x = size.width / 2, y = size.height + arrowHeight)
                lineTo(x = size.width / 2 - arrowWidth / 2, y = size.height)
                lineTo(x = 0 + cornerSize.width, y = size.height)
                arcTo(
                    rect = Rect(
                        Offset(x = 0f, y = size.height - bottomStartSize),
                        Size(width = bottomStartSize,height = bottomStartSize)
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(x = 0f, y = 0f)
            }
        )
    }
}

@Composable
fun Bubble() {
    Canvas(
        modifier = Modifier
    ) {
        val roundStart = size.height / 2
        val arrowHeight = 32F
        val arrowWidth = 64F
        drawRoundRect(
            Color.LightGray,
            size = Size(this.size.width, this.size.height),
            cornerRadius = CornerRadius(32f)
        )
        drawPath(
            path = Path().apply {
                lineTo(x = size.width / 2 - arrowWidth / 2, y = size.height)
                lineTo(x = size.width / 2, y = size.height + arrowHeight)
                lineTo(x = size.width / 2 + arrowWidth / 2, y = size.height)
                lineTo(x = size.width - roundStart, y = size.height)
                close()
            },
            Color.LightGray,
        )
    }
}

@Preview
@Composable
fun BubblePreview() {
    Bubble()
}
