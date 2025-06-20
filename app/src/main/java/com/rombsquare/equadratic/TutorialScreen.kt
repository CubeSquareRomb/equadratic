package com.rombsquare.equadratic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun getTutorialStrings(): List<TutorialStep> {
    return listOf(
        TutorialStep(
            stringResource(R.string.tut_introducing),
            stringResource(R.string.tut_introducing_text)
        ),

        TutorialStep(
            stringResource(R.string.tut_what_is_eq),
            stringResource(R.string.tut_what_is_eq_text)
        ),

        TutorialStep(
            stringResource(R.string.tut_step1),
            stringResource(R.string.tut_step1_text)
        ),

        TutorialStep(
            stringResource(R.string.tut_step2),
            stringResource(R.string.tut_step2_text)
        ),

        TutorialStep(
            stringResource(R.string.tut_modes),
            stringResource(R.string.tut_modes_text)
        ),
    )
}

data class TutorialStep(
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(navController: NavHostController) {
    val steps = getTutorialStrings()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tutorial") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .padding(8.dp)
        ) {
            LazyColumn {
                items(steps) { step ->
                    Column {
                        Text(step.title, fontSize=28.sp, fontWeight = FontWeight.Bold)
                        Text(step.description, color=Color.LightGray)
                        Spacer(Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}