package com.budget.project.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@Preview
fun DashboardPagePreview() {
    MaterialTheme {
        DashboardPage(
            navigateToBudget = {},
            viewModel = viewModel()
        )
    }
}

@Composable
@Preview
fun BudgetPagePreview() {
    MaterialTheme {
        BudgetPage(
            budgetId = 1,
            onBackPressed = {},
            viewModel = viewModel()
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