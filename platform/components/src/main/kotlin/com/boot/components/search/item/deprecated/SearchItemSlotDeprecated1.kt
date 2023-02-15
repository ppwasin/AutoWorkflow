package com.boot.components.search.item.deprecated

//
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.compose.ConstraintLayout
import com.boot.components.search.item.SearchItemPreviewProvider
import com.boot.components.search.item.SearchItemPreviewProvider.Data
import com.boot.components.search.item.SearchTextRow
import com.boot.components.utils.constraintVertical
import com.boot.designsystem.theme.material.AppMaterialTheme

@Composable
@Deprecated(
  "use SearchItemSlot instead",
  replaceWith =
    ReplaceWith(
      "SearchItemSlot(startIcon = startIcon, text = text, endIcon = endIcon)",
      imports =
        [
          "com.boot.components.search.item.SearchItemSlot",
          "androidx.compose.material.Text"
        ],
    ),
)
private fun SearchItemSlotDeprecated1(
  startIcon: @Composable () -> Unit,
  text: @Composable () -> Unit,
  endIcon: @Composable (() -> Unit)? = null
) {
  BoxWithConstraints {
    val boxWithConstraintsScope = this
    val iconSize = ButtonDefaults.IconSize
    val iconSpace = ButtonDefaults.IconSpacing
    val textWidth =
      boxWithConstraintsScope.maxWidth - (iconSize * 2 + iconSpace * 2)
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
          },
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
fun SearchItemSlotDeprecated1Preview(
  @PreviewParameter(SearchItemPreviewProvider::class) data: Data
) {
  AppMaterialTheme {
    SearchItemSlotDeprecated1(
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
