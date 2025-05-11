package com.budget.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.budget.project.data.BudgetDataRepository
import com.budget.project.ui.BudgetViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val budgetDataRepository = BudgetDataRepository()
        val viewModel = BudgetViewModel(budgetDataRepository)
        
        setContent {
            MainNavigator(
                viewModel = viewModel
            )
        }
    }
}