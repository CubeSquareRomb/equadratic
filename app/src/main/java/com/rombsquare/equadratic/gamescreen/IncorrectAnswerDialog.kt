package com.rombsquare.equadratic.gamescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun IncorrectAnswerDialog(
    onDismiss: () -> Unit

) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Oops") },
        text = {
            Column {
                Text("This answer is incorrect")
                Text("Your time was decreased by 20 seconds")
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}
