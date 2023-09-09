package com.boot.entrypoint.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun DFMHostStatusBar(status: HostDownloadStatus) {
	when (status) {
		is HostDownloadStatus.InProgress -> DFMHostProgressBar(status.percentage)
		HostDownloadStatus.Succees -> DFMHostSuccessBar()
		HostDownloadStatus.Error -> DFMHostErrorBar()
	}
}


@Composable
fun DFMHostProgressBar(percentage: String) {
	Text(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.Blue.copy(alpha = 0.5F))
			.padding(16.dp),
		text = "$percentage%",
		color = Color.Blue,
		textAlign = TextAlign.End,
	)
}

@Composable
fun DFMHostErrorBar() {
	Text(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.Red.copy(alpha = 0.5F))
			.padding(16.dp),
		text = "Error",
		color = Color.Red,
		textAlign = TextAlign.Start,
	)
}

@Composable
fun DFMHostSuccessBar() {
	Text(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.Green.copy(alpha = 0.5F))
			.padding(16.dp),
		text = "Success",
		color = Color.Green,
		textAlign = TextAlign.Start,
	)
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DFMHostStatusBarPreviewAnimate() {
	val status: HostDownloadStatus? = fakeState()
	Surface(Modifier.fillMaxSize()) {
		Box(Modifier.fillMaxSize()) {
			AnimatedVisibility(
				modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
				visible = status != null,
			) {
				status?.let {
					DFMHostStatusBar(status)
				}
			}

			Divider()

			AnimatedContent(
				modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
				targetState = status,
				contentKey = { it != null },
				contentAlignment = Alignment.TopCenter,
				label = ""
			) {status ->
				status?.let {
					DFMHostStatusBar(status)
				}
			}
		}

	}
}

@Composable
fun fakeState(): HostDownloadStatus? {
	var status: HostDownloadStatus? by remember {
		mutableStateOf(null)
	}

	LaunchedEffect(Unit) {
		while (true) {
			listOf(
				HostDownloadStatus.InProgress("25"),
				HostDownloadStatus.InProgress("50"),
				HostDownloadStatus.Error,
				HostDownloadStatus.Succees,
				null,
			).forEach {
				delay(1.seconds)
				status = it
			}
		}
	}

	return status
}

