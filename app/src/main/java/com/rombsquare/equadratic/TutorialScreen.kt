package com.rombsquare.equadratic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color


val steps = listOf(
    TutorialStep("Introducing", "Hey there! This game is all about solving quadratic equations fast! No need to be a math genius — just knowing basic operations like addition, subtraction, multiplication, and division is enough. This short tutorial will teach you how to crack quadratic equations like a pro. Let's go!"),
    TutorialStep("Step 1: Read the equation", "Each equation in this game is represented by three numbers. For example: [2, -10, 12]"),
    TutorialStep("Step 2: Simplify", "To make things easier, we want the first number to be 1. Here, we need to divide EVERY number by first number, so we get a new equation [1, -5, 6], which has the same solutions"),
    TutorialStep("Step 3: Sum and product", "We don't know the exact solutions yet, but we do know two things:\n\n1) The sum of solutions is equal to the second number, BUT WITH THE OPPOSITE SIGN\n2) The product of solutions is equal to the last number\n\nIn our example, the equation [1, -5, 6] has sum and product are 5 and 6 respectively. Memorize these two numbers"),
    TutorialStep("Step 4: Trial and error", "Now try to find two numbers that\n\n* Add up to 5\n* Multiply to 6\n\nAfter testing a few options, you’ll find that 2 and 3 are the solutions because:\n\n2 + 3 = 5\n2 * 3 = 6\n\nThose numbers (2, 3) are the solutions to the equation!"),
    TutorialStep("Modes", "Now, let's talk about modes. This game has these modes (difficulties):\n\n" +
            "1) Easy - the equation only has positive roots, each no greater than 10. Also, the first number equals to 1\n" +
            "2) Medium - like easy, but it may have negative roots\n" +
            "3) Hard - Like Medium, but the first number can be any number in the range from -10 to 10\n" +
            "4) Growth - Starts with easy equations and gradually increases to the hardest"),
)


data class TutorialStep(
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tutorial") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                    Text(step.title, fontSize=28.sp)
                    Text(step.description, color=Color.LightGray)
                    Spacer(Modifier.size(20.dp))
                }
            }
        }
    }
}