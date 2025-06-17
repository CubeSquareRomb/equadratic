package com.rombsquare.equadratic.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rombsquare.equadratic.gamescreen.Eq
import com.rombsquare.equadratic.gamescreen.getEqByDiff
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavController, innerPadding: PaddingValues) {
    var eq by remember { mutableStateOf(Eq(1, -5, 6)) }
    var showPlayDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            eq = getEqByDiff("hard")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "Equadratic",
                fontSize = 32.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                eq.format(),
                fontSize = 24.sp,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircleButton(
                    diameter = 60.dp,
                    icon = Icons.AutoMirrored.Filled.HelpOutline,
                ) {
                    navController.navigate("tutorial")
                }

                Spacer(Modifier.weight(1f))

                CircleButton(
                    diameter = 100.dp,
                    icon = Icons.Default.PlayArrow,
                ) {
                    showPlayDialog = true
                }

                Spacer(Modifier.weight(1f))

                CircleButton(
                    diameter = 60.dp,
                    icon = Icons.Filled.Settings,
                ) {
                    showSettingsDialog = true
                }
            }
        }


        if (showPlayDialog) {
            PlayDialog(
                onClick = {
                    navController.navigate("game/$it")
                },
                onDismiss = {
                    showPlayDialog = false
                }
            )
        }

        if (showSettingsDialog) {
            SettingsDialog(
                onDismiss = {
                    showSettingsDialog = false
                }
            )
        }

    }
}