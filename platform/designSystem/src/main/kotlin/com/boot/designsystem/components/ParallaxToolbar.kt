package com.boot.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.boot.designSystem.R
import com.boot.designsystem.theme.app.AppTheme
import com.boot.designsystem.theme.material.AppTypography
import com.boot.designsystem.theme.material.Shapes
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min

@Composable
fun ParallaxToolbar(
  scrollState: LazyListState,
  AppBarExpendedHeight: Dp = AppTheme.dimensions.AppBarExpendedHeight,
  AppBarCollapsedHeight: Dp = AppTheme.dimensions.AppBarCollapsedHeight
) {
  val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

  val maxOffset =
    with(LocalDensity.current) { imageHeight.roundToPx() } -
      LocalWindowInsets.current.systemBars.layoutInsets.top
  val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)
  val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

  Box {
    TopAppBar(
      contentPadding = PaddingValues(),
      backgroundColor = White,
      modifier = Modifier.height(AppBarExpendedHeight).offset { IntOffset(x = 0, y = -offset) },
      elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
      Column {
        // Title Background with transparent effect
        Box(Modifier.height(imageHeight).graphicsLayer { alpha = 1f - offsetProgress }) {
          Image(
            painter = painterResource(id = R.drawable.strawberry_pie_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
          )
          Box(
            modifier =
              Modifier.fillMaxSize()
                .background(
                  Brush.verticalGradient(
                    colorStops = arrayOf(Pair(0.4f, Transparent), Pair(1f, White))
                  )
                )
          )

          Row(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.Bottom
          ) {
            Text(
              "Category",
              fontWeight = FontWeight.Medium,
              modifier =
                Modifier.clip(Shapes.small)
                  .background(LightGray)
                  .padding(vertical = 6.dp, horizontal = 16.dp)
            )
          }
        }
        Column(
          Modifier.fillMaxWidth().height(AppBarCollapsedHeight),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = "Recipe Title",
            style = AppTheme.typography.title,
            modifier = Modifier.padding(AppTheme.dimensions.paddingLarge)
          )
        }
      }
    }
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier =
        Modifier.fillMaxWidth()
          .statusBarsPadding()
          .height(AppBarCollapsedHeight)
          .padding(horizontal = 16.dp)
    ) {
      CircularButton(R.drawable.ic_arrow_back)
      CircularButton(R.drawable.ic_favorite)
    }
  }
}

@Preview(showBackground = true, widthDp = 380, heightDp = 1400)
@Composable
fun ParallaxToolbarPreview() {
  val scrollState = rememberLazyListState()
  //  Box {
  ParallaxToolbar(scrollState)
  //  }
}

val LightGray = Color(0xFFF7F7F7)
val Transparent = Color(0x00FFFFFF)
val White = Color(0xFFFFFFFF)

@Composable
fun CircularButton(
  @DrawableRes iconResouce: Int,
  color: Color = Gray,
  elevation: ButtonElevation? = ButtonDefaults.elevation(),
  onClick: () -> Unit = {}
) {
  Button(
    onClick = onClick,
    contentPadding = PaddingValues(),
    shape = Shapes.small,
    colors = ButtonDefaults.buttonColors(backgroundColor = White, contentColor = color),
    elevation = elevation,
    modifier = Modifier.width(38.dp).height(38.dp)
  ) { Icon(painterResource(id = iconResouce), null) }
}
