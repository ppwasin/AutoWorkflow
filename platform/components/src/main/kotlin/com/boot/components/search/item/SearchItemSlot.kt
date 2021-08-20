package com.boot.components.search.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.compose.ConstraintLayout
import com.boot.components.utils.constraintVertical
import com.boot.theme.AppTheme

@Composable
fun SearchItemSlot(
  startIcon: @Composable () -> Unit,
  text: @Composable () -> Unit,
  endIcon: @Composable (() -> Unit)? = null
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
fun SearchItemSlotPreview(
  @PreviewParameter(SearchItemPreviewProvider::class) data: SearchItemPreviewProvider.Data
) {
  AppTheme {
    SearchItemSlot(
      startIcon = {
        Icon(
          Icons.Default.AccessAlarm,
          contentDescription = "SearchStartIcon",
        )
      },
      text = {
        Text(
          data.text,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          textAlign = TextAlign.Start,
        )
      },
      endIcon = data.endIcon
    )
  }
}
