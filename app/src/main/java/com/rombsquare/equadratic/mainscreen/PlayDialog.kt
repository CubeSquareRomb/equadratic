package com.rombsquare.equadratic.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun PlayDialog(
    onClick: (String) -> Unit,
    onDismiss: () -> Unit

) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose difficulty") },
        text = {
            Column {
                TextButton(
                    onClick = {onClick("easy")}
                ) {
                    Text("Easy")
                }

                TextButton(
                    onClick = {onClick("medium")}
                ) {
                    Text("Medium")
                }

                TextButton(
                    onClick = {onClick("hard")}
                ) {
                    Text("Hard")
                }

                TextButton(
                    onClick = {onClick("growth")}
                ) {
                    Text("Growth")
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}
