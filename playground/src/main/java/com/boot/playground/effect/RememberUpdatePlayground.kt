package com.boot.playground.effect

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*

@Composable
fun RememberUpdatePlayground() {
    var myInput by remember { mutableStateOf(0) }

    Column {
        OutlinedButton(onClick = { myInput++ }) { Text("Increase $myInput") }
        Calculation(input = myInput)
    }
}