package com.boot.autoworkflow

import android.content.Context
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.airbnb.android.showkase.annotation.ShowkaseRoot
import com.airbnb.android.showkase.annotation.ShowkaseRootModule
import com.airbnb.android.showkase.models.Showkase

@ShowkaseRoot
class MyRootModule: ShowkaseRootModule

object ShowkaseInit {
	fun addShortcut(context: Context){
		val intent = Showkase.getBrowserIntent(context).apply {
			action = Intent.ACTION_VIEW
		}
		val shortcut = ShortcutInfoCompat.Builder(context, "showkase-shortcut")
			.setShortLabel("Showkase")
			.setIntent(intent)
			.build()

		ShortcutManagerCompat.pushDynamicShortcut(context, shortcut)
	}
}
