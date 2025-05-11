package com.budget.project.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.budget.project.data.BudgetDataRepository

@Composable
@Preview
fun DashboardPagePreview() {
    val budgetDataRepository = BudgetDataRepository()

    MaterialTheme {
        DashboardPage(
            navigateToBudget = {},
            viewModel = BudgetViewModel(budgetDataRepository)
        )
    }
}

@Composable
@Preview
fun BudgetPagePreview() {
    val budgetDataRepository = BudgetDataRepository()
    MaterialTheme {
        BudgetPage(
            budgetId = 1,
            onBackPressed = {},
            viewModel = BudgetViewModel(budgetDataRepository)
        )
    }
}

@Composable
@Preview
fun DropDownPreview() {
    MaterialTheme {
        TimeFrameDropDown(
            TimeFrame.FORTNIGHTLY
        )
    }
}