package com.boot.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.boot.components.utils.getRandomString
import com.boot.designsystem.theme.material.AppMaterialTheme

@Composable
fun SubTitle(text: String) {
	Text(
		text = text,
		overflow = TextOverflow.Ellipsis,
		style = MaterialTheme.typography.titleSmall,
		maxLines = 2,
	)
}

@Preview(showSystemUi = true)
@Composable
fun SubTitlePreview() {
	AppMaterialTheme { SubTitle(getRandomString(100)) }
}
