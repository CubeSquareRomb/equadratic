package com.rombsquare.equadratic

import android.content.Context
import androidx.core.content.edit

class PrefsHelper(context: Context) {
    private val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    fun saveClassicView(enabled: Boolean) {
        prefs.edit { putBoolean("classic_view", enabled) }
    }

    fun isClassicView(): Boolean {
        return prefs.getBoolean("classic_view", false)
    }
}