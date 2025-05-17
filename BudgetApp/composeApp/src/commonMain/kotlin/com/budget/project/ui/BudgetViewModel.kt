package com.budget.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budget.project.data.BudgetDataRepository
import kotlinx.coroutines.launch

enum class TimeFrame(val displayName: String) {
    WEEKLY("Weekly"),
    FORTNIGHTLY("Fortnightly"),
    MONTHLY("Monthly"),
    YEARLY("Yearly")
}

data class Budget(
    val id: Int,
    val name: String,
    val timeFrame: TimeFrame,
    val expenses: List<MoneyEntry>,
    val incomes: List<MoneyEntry>,
    val savings: List<MoneyEntry>
)

data class MoneyEntry(
    val name: String,
    val amount: Double,
    val timeFrame: TimeFrame,
    val isEnabled: Boolean
)

class BudgetViewModel(
    private val budgetDataRepository: BudgetDataRepository
) : ViewModel() {
    fun getBudgetNames(): List<String> {
        return budgetDataRepository.getAllBudgetNames()
    }

    fun getBudgetIdFromName(budgetName: String): Int {
        return budgetDataRepository.getBudgetIdByName(budgetName)
    }

    fun getBudget(budgetId: Int): Budget {
        return budgetDataRepository.getBudgetById(budgetId)
    }
}