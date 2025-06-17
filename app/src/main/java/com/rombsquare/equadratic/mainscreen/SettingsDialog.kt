package com.rombsquare.equadratic.mainscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = viewModel()
) {
    var isClassicView = viewModel.isClassicView


    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Settings")
        },
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isClassicView,
                    onCheckedChange = {
                        isClassicView = it
                        viewModel.onClassicViewChanged(it)
                    }
                )

                Text("Classic view of equation")
            }

        },
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("OK")
            }
        },
        dismissButton = {}
    )
}