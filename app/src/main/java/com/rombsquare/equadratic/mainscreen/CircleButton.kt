package com.rombsquare.equadratic.mainscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(diameter: Dp, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(diameter)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = CircleShape
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Icon(icon, contentDescription = "Play", tint = Color.White, modifier = Modifier.size(diameter/2))
    }
}