package com.boot.designsystem.transition

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.intermediateLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

fun Modifier.shareElementV2(
	isRunning: Boolean
) = composed {
	val offsetAnimation: DeferredAnimation<IntOffset, AnimationVector2D> = remember { DeferredAnimation(IntOffset.VectorConverter) }
	val sizeAnimation: DeferredAnimation<IntSize, AnimationVector2D> = remember { DeferredAnimation(IntSize.VectorConverter) }


	this.intermediateLayout { measurable, constraints ->
		println("isRunning: $isRunning")
		if(!isRunning){
			val placeable = measurable.measure(constraints)
			layout(placeable.width, placeable.height) {
				println("SPEC, actually place child")
				placeable.place(0, 0)
			}
		}
		else {
			val (width, height) = sizeAnimation.updateTarget(
				lookaheadSize, spring(stiffness = Spring.StiffnessMediumLow)
			)
			val animatedConstraints = Constraints.fixed(width, height)
			val placeable = measurable.measure(animatedConstraints)
			layout(placeable.width, placeable.height) {
				val (x, y) = offsetAnimation.updateTargetBasedOnCoordinates(
					spring(stiffness = Spring.StiffnessMediumLow),
				)
				placeable.place(x, y)
			}
		}
	}
}
