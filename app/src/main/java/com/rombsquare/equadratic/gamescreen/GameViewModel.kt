package com.rombsquare.equadratic.gamescreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rombsquare.equadratic.PrefsHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    application: Application,
    val diff: String
) : ViewModel() {
    private var _solved = MutableStateFlow(0)
    val solved: StateFlow<Int> = _solved

    private var _eq = MutableStateFlow(Eq(1, 1, 1))
    val eq: StateFlow<Eq> = _eq

    private var _maxRootAsInput = MutableStateFlow(true)
    val maxRootAsInput: StateFlow<Boolean> = _maxRootAsInput

    private var _answer = MutableStateFlow(0)
    val answer: StateFlow<Int> = _answer

    private var _time = MutableStateFlow(120f) // 2 minutes
    val time: StateFlow<Float> = _time

    private val prefs = PrefsHelper(application.applicationContext)
    var isClassicView = prefs.isClassicView()

    init {
        reset()
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_time.value > 0) {
                delay(10)
                _time.value -= .01f
            }
        }
    }

    fun next(userAnswer: Int): Boolean {
        if (userAnswer == answer.value) {
            _solved.value++
            _eq.value = if(diff == "growth") getEqByLevel(_solved.value) else getEqByDiff(diff)
            _maxRootAsInput.value = (0..1).random() == 1
            _answer.value = if(_maxRootAsInput.value) _eq.value.x2 else _eq.value.x1
            return true
        }
        return false
    }

    fun reset() {
        viewModelScope.launch {
            isClassicView = prefs.isClassicView()
            _time.value = 120f
            _solved.value = 0
            _eq.value = if(diff == "growth") getEqByLevel(_solved.value) else getEqByDiff(diff)
            _maxRootAsInput.value = (0..1).random() == 1
            _answer.value = if(_maxRootAsInput.value) _eq.value.x2 else _eq.value.x1
        }
    }
}

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(
    private val app: Application,
    private val diff: String,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(app, diff) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}