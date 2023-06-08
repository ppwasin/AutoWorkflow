package com.boot.playground.uri

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.net.URLEncoder

@Composable
fun UriPlayground() {
	val encodedKey = URLEncoder.encode("Hotel Name", "utf-8").replace("+", "%20")
	val encodedValue = URLEncoder.encode("中文", "utf-8").replace("+", "%20")
	val uri1 =
		Uri.parse(
			"agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2&$encodedKey=$encodedValue",
		)

	val encodedKey2 = URLEncoder.encode("Hotel Name", "utf-8")
	val encodedValue2 = URLEncoder.encode("中文", "utf-8")
	val uri2 =
		Uri.parse(
			"agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2&$encodedKey2=$encodedValue2",
		)

	val uri3 =
		Uri.parse("agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2")
			.buildUpon()
			.appendQueryParameter("Hotel Name", "中文")
			.build()

	Column {
		Text("Test URL1: $uri1")
		Text("Test URL2: $uri2")
		Text("Test URL3: $uri3")
		Text("Host: ${uri3.host}")
		Text("Path: ${uri3.pathSegments}")
	}
}
