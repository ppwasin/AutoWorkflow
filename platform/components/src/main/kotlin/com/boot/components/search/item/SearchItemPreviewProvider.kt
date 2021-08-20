package com.boot.components.search.item

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.boot.components.search.item.SearchItemPreviewProvider.Data
import com.boot.components.utils.combineLatest
import com.boot.components.utils.getRandomString

class SearchItemPreviewProvider(
  override val values: Sequence<Data> =
    textParams.combineLatest(iconParam) { text, icon -> Data(text, icon) }.asSequence()
) : PreviewParameterProvider<Data> {
  data class Data(val text: String, val endIcon: @Composable (() -> Unit)?)
  companion object {
    private val textParams: List<String> = listOf(getRandomString(100), getRandomString(10))
    private val iconParam: List<@Composable (() -> Unit)?> =
      listOf(
        {
          Icon(
            Icons.Default.ZoomIn,
            contentDescription = "SearchEndIcon",
          )
        },
        null
      )
  }
}
