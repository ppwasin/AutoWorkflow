package com.boot.external.appsflyerwrapper.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.boot.designsystem.theme.material.AppMaterialTheme

@Composable
fun AppsflyerScreen() {
	val context = LocalContext.current
	val deeplinkValue = remember { (context as Activity).intent.data }
	AppMaterialTheme {
		Surface {
			Column(
				verticalArrangement = Arrangement.spacedBy(8.dp),
				modifier =
				Modifier
					.fillMaxSize()
					.padding(16.dp)
					.verticalScroll(rememberScrollState()),
			) {
				Text("DeeplinkValue", fontWeight = FontWeight.Bold)
				Text(deeplinkValue?.toString() ?: "Empty")
			}
		}
	}
}
