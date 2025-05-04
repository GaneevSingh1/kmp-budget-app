package com.budget.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

val budgets = listOf("Budget 1", "Budget 2", "Budget 3")

@Composable
fun DashboardPage() {
   Scaffold (
       topBar = { AppTopBar() }
   ) { padding ->
       Column(
           modifier = Modifier.padding(padding)
       ) {
           LazyVerticalGrid(
               columns = GridCells.Fixed(1)
           ) {
               items(budgets.size) { index ->
                   BudgetItem(budgets[index])
               }
           }
       }
   }
}

@Composable
fun BudgetItem(name: String) {
    Text(name)
}

@Composable
fun AppTopBar() {
    Row(
        modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .padding(
                vertical = 10.dp,
                horizontal = 20.dp
            )
    ) {
        Text(
            text = "Budget it!",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}