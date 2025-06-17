package com.rombsquare.equadratic.gamescreen

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rombsquare.equadratic.PrefsHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun GameScreen(navController: NavController, innerPadding: PaddingValues, diff: String) {
    val context = LocalContext.current
    val app = context.applicationContext as Application

    val factory = remember { GameViewModelFactory(app, diff) }
    val gameViewModel: GameViewModel = viewModel(factory = factory)

    val solved by gameViewModel.solved.collectAsState()
    val time by gameViewModel.time.collectAsState()
    val maxRootAsInput by gameViewModel.maxRootAsInput.collectAsState()
    val eq by gameViewModel.eq.collectAsState()

    var showIncorrectAnswerDialog by remember { mutableStateOf(false) }

    if (time <= 0) {
        EndDialog(solved) {
            navController.navigate("main")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            LinearProgressIndicator(
                progress = time / 120f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = getColorForProgressbar(time / 120f)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val intTime = time.toInt()
                Text(String.format(Locale.US, "%02d:%02d", intTime/60, intTime%60))
                Spacer(modifier = Modifier.weight(1f))
                Text("Solved: $solved")
            }
        }


        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(if(maxRootAsInput) "Find the biggest root" else "Find the smallest root")
            Text(
                eq.format(gameViewModel.isClassicView),
                fontSize=28.sp,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        AnswerInput(Modifier) {
            val state = gameViewModel.next(it.toInt())

            if (!state) {
                showIncorrectAnswerDialog = true
            }
        }

        if (showIncorrectAnswerDialog) {
            IncorrectAnswerDialog {
                showIncorrectAnswerDialog = false
            }
        }
    }
}

fun getColorForProgressbar(progress: Float): Color {
    if (progress > .5f) {
        return Color(255 - (progress*255*2).toInt(), 255, 0)
    }
    return Color(255, (progress*255*2).toInt(), 0)
}