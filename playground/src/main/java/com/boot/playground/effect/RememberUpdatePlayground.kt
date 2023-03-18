package com.boot.playground.effect

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun RememberUpdatePlayground() {
	var myInput by remember { mutableStateOf(0) }

	Column {
		OutlinedButton(onClick = { myInput++ }) { Text("Increase $myInput") }
		Calculation(input = myInput)
	}
}
