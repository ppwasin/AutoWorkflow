package com.boot.components.utils

import androidx.constraintlayout.compose.ConstrainScope

fun ConstrainScope.constraintVertical() {
  top.linkTo(parent.top)
  bottom.linkTo(parent.bottom)
}
