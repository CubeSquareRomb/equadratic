package com.rombsquare.equadratic.gamescreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun EndDialog(
    solved: Int,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Game Over") },
        text = {
            Text("You solved $solved equations")
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Home")
            }
        },
    )
}