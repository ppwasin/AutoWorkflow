package com.boot.designsystem.theme.app

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.boot.designSystem.R

private val rubik = FontFamily(Font(R.font.rubik_regular, FontWeight.Normal))

private val openSans = FontFamily(Font(R.font.open_sans_regular, FontWeight.Normal))

data class AppTypography(
  val title: TextStyle =
    TextStyle(fontFamily = rubik, fontWeight = FontWeight.Bold, fontSize = 26.sp),
  val h1: TextStyle =
    TextStyle(fontFamily = rubik, fontWeight = FontWeight.Normal, fontSize = 26.sp),
  val subtitle: TextStyle =
    TextStyle(fontFamily = openSans, fontWeight = FontWeight.Normal, fontSize = 16.sp),
  val body: TextStyle =
    TextStyle(fontFamily = openSans, fontWeight = FontWeight.Normal, fontSize = 16.sp),
  val button: TextStyle =
    TextStyle(fontFamily = rubik, fontWeight = FontWeight.Normal, fontSize = 16.sp),
  val caption: TextStyle =
    TextStyle(fontFamily = openSans, fontWeight = FontWeight.Normal, fontSize = 12.sp)
) : Parcelable {
  constructor(
    parcel: Parcel
  ) : this(TODO("h1"), TODO("subtitle"), TODO("body"), TODO("button"), TODO("caption")) {}

  override fun writeToParcel(parcel: Parcel, flags: Int) {}

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<AppTypography> {
    override fun createFromParcel(parcel: Parcel): AppTypography {
      return AppTypography(parcel)
    }

    override fun newArray(size: Int): Array<AppTypography?> {
      return arrayOfNulls(size)
    }
  }
}

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }
