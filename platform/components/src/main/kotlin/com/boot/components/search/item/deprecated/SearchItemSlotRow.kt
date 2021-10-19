package com.boot.components.search.item.deprecated

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.boot.components.search.item.SearchItemPreviewProvider
import com.boot.designsystem.theme.material.AppMaterialTheme

@Composable
@Deprecated(
  "use SearchItemSlot instead",
  replaceWith =
    ReplaceWith(
      "SearchItemSlot(startIcon = { startIcon }, text = { Text(text) }, endIcon = { endIcon })",
      imports = ["com.boot.components.search.item.SearchItemSlot", "androidx.compose.material.Text"]
    )
)
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

@ExperimentalComposeUiApi
@Preview(device = Devices.PIXEL_2_XL, showBackground = true)
@Composable
internal fun SearchItemSlotPreview(
  @PreviewParameter(SearchItemPreviewProvider::class) text: String
) {
  AppMaterialTheme {
    SearchItemSlotRow(
      startIcon = Icons.Default.AccessAlarm,
      text = text,
      endIcon = Icons.Default.ZoomIn,
    )
  }
}
