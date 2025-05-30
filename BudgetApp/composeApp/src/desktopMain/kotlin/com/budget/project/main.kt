package com.budget.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.budget.project.data.BudgetDataRepository
import com.budget.project.ui.BudgetViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BudgetApp",
    ) {
        val budgetDataRepository = BudgetDataRepository()
        val viewModel = BudgetViewModel(budgetDataRepository)
        MainNavigator(
            viewModel = viewModel
        )
    }
}