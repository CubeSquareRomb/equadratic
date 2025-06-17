package com.rombsquare.equadratic.mainscreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.rombsquare.equadratic.PrefsHelper

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = PrefsHelper(application.applicationContext)

    var isClassicView by mutableStateOf(prefs.isClassicView())
        private set

    fun onClassicViewChanged(value: Boolean) {
        isClassicView = value
        prefs.saveClassicView(value)
    }
}
