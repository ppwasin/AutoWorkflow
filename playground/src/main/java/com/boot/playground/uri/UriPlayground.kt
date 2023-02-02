package com.boot.playground.uri

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.net.URI
import java.net.URLEncoder

@Composable
fun UriPlayground() {
  val encodedKey2 = URLEncoder.encode("Hotel Name", "utf-8")
  val encodedValue2 = URLEncoder.encode("中文", "utf-8")
  val javaUri =
    Uri.parse(
      "agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2&$encodedKey2=$encodedValue2"
    )

  val androidUri = Uri.parse("agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2&Hotel%20Name=中文")


  val encodedKey = URLEncoder.encode("Hotel Name", "utf-8").replace("+", "%20")
  val encodedValue = URLEncoder.encode("中文", "utf-8").replace("+", "%20")
  val javaUriWithReplace =
    Uri.parse(
      "agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2&$encodedKey=$encodedValue"
    )

  val specialUri = "agoda://hotel/쏠비치 양양/181785?search=1&checkin=2023-03-05&checkout=2023-03-06&adults=2&children=0&rooms=1&site_id=1887514&cinfo=20230202_507875"
  val javaSpeicalUri = runCatching { URI(specialUri) }.getOrNull()?.toString()
  val androidSpecialUri = Uri.parse(specialUri)


  Column(
    modifier = Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Text("Java URI: $javaUri")
    Text("Android URI: $androidUri")
    Text("Java URI with encode: $javaUriWithReplace")

    Divider()
    Text("Invalid URI")
    Text("URI(specialUri): $javaSpeicalUri")
    Text("Uri.parse(specialUri): $androidSpecialUri")

  }
}
//http://agoda.onelink.me/1640755593?pid=Mobile_App&c=Property_Sharing_V1&site_id=1770664&af_dp=agoda%3a%2f%2fhotel%2fStation%20Phu%20Thap%20Buek%20Resort%2f25951770&deep_link_value=agoda%3a%2f%2fhotel%2fStation%20Phu%20Thap%20Buek%20Resort%2f25951770&af_force_dp=true&af_r=https%3a%2f%2fwww.agoda.com%2fstation-phu-thap-buek-resort%2fhotel%2fphetchabun-th.html&af_web_dp=https%3a%2f%2fwww.agoda.com%2fstation-phu-thap-buek-resort%2fhotel%2fphetchabun-th.html&adults=2&rooms=1&checkIn=2023-04-07&checkOut=2023-04-08&los=1&cid=1770664&af_sub1=EXP-ID-1052969%3AA&af_sub2=EXP-RUN-ID-1220053%3AA
// http://agoda.onelink.me/1640755593?pid=Mobile_App&c=Property_Sharing_V1&site_id=1770664&af_dp=agoda%3a%2f%2fhotel%2fPattaya%e4%b8%ad%e5%bf%83%e6%b5%b7%e5%85%ac%e5%af%93%20%e0%b9%80%e0%b8%8b%e0%b9%87%e0%b8%99%e0%b8%97%e0%b8%a3%e0%b8%b4%e0%b8%84%20%e0%b8%8b%e0%b8%b5%20%e0%b8%9e%e0%b8%b1%e0%b8%97%e0%b8%a2%e0%b8%b2%e4%b8%ad%e6%96%87%e6%88%bf%e4%b8%9c%2f36551330&deep_link_value=agoda%3a%2f%2fhotel%2fPattaya%e4%b8%ad%e5%bf%83%e6%b5%b7%e5%85%ac%e5%af%93%20%e0%b9%80%e0%b8%8b%e0%b9%87%e0%b8%99%e0%b8%97%e0%b8%a3%e0%b8%b4%e0%b8%84%20%e0%b8%8b%e0%b8%b5%20%e0%b8%9e%e0%b8%b1%e0%b8%97%e0%b8%a2%e0%b8%b2%e4%b8%ad%e6%96%87%e6%88%bf%e4%b8%9c%2f36551330&af_force_dp=true&af_r=https%3a%2f%2fwww.agoda.com%2fpattaya-h36551330%2fhotel%2fpattaya-th.html&af_web_dp=https%3a%2f%2fwww.agoda.com%2fpattaya-h36551330%2fhotel%2fpattaya-th.html&adults=2&rooms=1&checkIn=2023-04-07&checkOut=2023-04-08&los=1&cid=1770664&af_sub1=EXP-ID-1052969%3AA&af_sub2=EXP-RUN-ID-1220053%3AA

//Cannot encode uri
/*
agoda://hotel/쏠비치 양양/181785?search=1&checkin=2023-03-05&checkout=2023-03-06&adults=2&children=0&rooms=1&site_id=1887514&cinfo=20230202_507875
* */
