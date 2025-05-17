package com.budget.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage(
    navigateToBudget: (String) -> Unit,
    viewModel: BudgetViewModel
) {
   Scaffold (
       topBar = {
           TopAppBar(
               title = { Text("Budget it!") },
               colors = TopAppBarColors(
                   containerColor = MaterialTheme.colorScheme.primaryContainer,
                   scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                   navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                   titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                   actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
               )
           )
        },
   ) { padding ->
       Column(
           modifier = Modifier.padding(padding)
               .fillMaxWidth()
       ) {
           LazyVerticalGrid(
               columns = GridCells.Fixed(1),
               contentPadding = PaddingValues(10.dp),
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.Center
           ) {
               items(viewModel.getBudgetNames()) {
                   BudgetItem(
                       name = it,
                       onClick = navigateToBudget
                   )
               }
           }
       }
   }
}

@Composable
fun BudgetItem(
    name: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 10.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ){
        Button(
            onClick = { onClick(name) }
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp)

            )
        }
    }
}