package com.budget.project.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun TimeFrameDropDown(
    defaultTimeFrame: TimeFrame
) {
    val options = TimeFrame.entries.map { it.displayName }

    val expanded = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf(defaultTimeFrame.displayName) }

    Box {
        TextButton(
            onClick = { expanded.value = true },
            colors = basicButtonColors,
        ) {
            Text(text = selectedOption.value)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption.value = option
                        expanded.value = false
                    },
                )
            }
        }
    }
}