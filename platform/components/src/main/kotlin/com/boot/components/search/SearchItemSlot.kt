package com.boot.components.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.constraintlayout.compose.ConstraintLayout
import com.boot.components.search.SearchItemSlotLayoutType.ConstraintLayout
import com.boot.components.search.SearchItemSlotLayoutType.Row
import com.boot.components.utils.constraintVertical
import com.boot.components.utils.getRandomString
import com.boot.theme.AppTheme

enum class SearchItemSlotLayoutType {
  Row,
  ConstraintLayout
}

@Composable
fun SearchItemSlot(
  startIcon: ImageVector,
  text: String,
  endIcon: ImageVector?,
  layoutType: SearchItemSlotLayoutType = ConstraintLayout
) {
  when (layoutType) {
    Row -> SearchItemSlotRow(startIcon, text, endIcon)
    ConstraintLayout ->
      SearchItemConstraintLayout(
        startIcon = {
          Icon(
            startIcon,
            contentDescription = "SearchStartIcon",
          )
        },
        text = {
          Text(
            text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
          )
        },
        endIcon = {
          Icon(
            endIcon!!,
            contentDescription = "SearchEndIcon",
          )
        }
      )
  }
}

@Composable
private fun SearchItemSlotRow(startIcon: ImageVector, text: String, endIcon: ImageVector?) {
  BoxWithConstraints {
    val boxWithConstraintsScope = this
    val iconSize = ButtonDefaults.IconSize
    val iconSpace = ButtonDefaults.IconSpacing
    val textWidth = boxWithConstraintsScope.maxWidth - (iconSize * 2 + iconSpace * 2)
    val verticalAlignment = Alignment.CenterVertically
    Row(
      Modifier.fillMaxWidth(),
      verticalAlignment = verticalAlignment,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = verticalAlignment) {
        Icon(startIcon, contentDescription = "SearchStartIcon", modifier = Modifier.size(iconSize))

        Spacer(Modifier.size(iconSpace))
        Text(
          text = "$textWidth: $text",
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.width(textWidth),
          textAlign = TextAlign.Start
        )
      }
      if (endIcon != null)
        Row(verticalAlignment = verticalAlignment) {
          Icon(endIcon, contentDescription = "SearchEndIcon", modifier = Modifier.size(iconSize))
        }
    }
  }
}

@Composable
private fun SearchItemConstraintLayout(
  startIcon: @Composable () -> Unit,
  text: @Composable () -> Unit,
  endIcon: @Composable (() -> Unit)?
) {
  BoxWithConstraints {
    val boxWithConstraintsScope = this
    val iconSize = ButtonDefaults.IconSize
    val iconSpace = ButtonDefaults.IconSpacing
    val textWidth = boxWithConstraintsScope.maxWidth - (iconSize * 2 + iconSpace * 2)
    ConstraintLayout(Modifier.fillMaxWidth().wrapContentHeight()) {
      val (startIconConst, textConst, endIconConst) = createRefs()
      Box(
        Modifier.size(ButtonDefaults.IconSize).constrainAs(startIconConst) {
          constraintVertical()
          start.linkTo(parent.start)
        },
      ) { startIcon() }

      Box(
        modifier =
          Modifier.width(textWidth).constrainAs(textConst) {
            constraintVertical()
            start.linkTo(startIconConst.end, margin = iconSpace)
          }
      ) { text() }

      if (endIcon != null)
        Box(
          modifier =
            Modifier.size(ButtonDefaults.IconSize).constrainAs(endIconConst) {
              constraintVertical()
              end.linkTo(parent.end)
            },
        ) { endIcon() }
    }
  }
}

@ExperimentalComposeUiApi
@Preview(device = Devices.PIXEL_2_XL, showBackground = true)
@Composable
fun SearchItemSlotPreview(@PreviewParameter(SearchItemPreviewProvider::class) text: String) {
  AppTheme {
    SearchItemSlot(
      startIcon = Icons.Default.AccessAlarm,
      text = text,
      endIcon = Icons.Default.ZoomIn,
      layoutType = Row
    )
  }
}

class SearchItemPreviewProvider(
  override val values: Sequence<String> = sequenceOf(getRandomString(10), getRandomString(100))
) : PreviewParameterProvider<String>

@ExperimentalComposeUiApi
@Preview(device = Devices.PIXEL_2_XL, showBackground = true)
@Composable
fun SearchItemConstraintPreview(@PreviewParameter(SearchItemPreviewProvider::class) text: String) {
  AppTheme {
    SearchItemSlot(
      startIcon = Icons.Default.AccessAlarm,
      text = text,
      endIcon = Icons.Default.ZoomIn,
      layoutType = ConstraintLayout
    )
  }
}
