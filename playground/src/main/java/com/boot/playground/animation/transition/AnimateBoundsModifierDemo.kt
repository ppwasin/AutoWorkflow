package com.boot.playground.animation.transition

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boot.designsystem.transition.animateBounds
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnimateBoundsModifierDemo() {
	var height by remember {
		mutableStateOf(200)
	}
	var left by remember {
		mutableStateOf(0)
	}
	var top by remember {
		mutableStateOf(0)
	}
	var right by remember {
		mutableStateOf(0)
	}
	var bottom by remember {
		mutableStateOf(0)
	}
	var weight by remember {
		mutableStateOf(2f)
	}

	LookaheadScope {
		Column(
			Modifier
				.fillMaxSize()
				.clickable {
					height = Random.nextInt(10, 300)
					weight = Random
						.nextDouble(0.5, 4.5)
						.toFloat()

					left = Random.nextInt(0, 200)
					top = Random.nextInt(0, 100)
					right = Random.nextInt(0, 200)
					bottom = Random.nextInt(0, 100)
				}
		) {
			Box(
				Modifier
					.fillMaxHeight(0.5f)
					.fillMaxSize()
			) {
				Box(
					Modifier
						.background(Color.Gray)
						.animateBounds(
							Modifier.padding(left.dp, top.dp, right.dp, bottom.dp)
						)
						.background(Color.Red)
						.fillMaxSize()
				)
			}
			Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
				Box(
					Modifier
						.animateBounds(
							Modifier
								.weight(weight)
								.height(height.dp)
						)
						.background(Color(0xffa2d2ff), RoundedCornerShape(5.dp))
				)
				Box(
					Modifier
						.animateBounds(
							Modifier
								.weight(1f)
								.height(height.dp)
						)
						.background(Color(0xfffff3b0))
				)
			}
		}
	}
}

@Preview
@Composable
fun AnimateBoundsModifierDemoPreview() {
	AnimateBoundsModifierDemo()
}
