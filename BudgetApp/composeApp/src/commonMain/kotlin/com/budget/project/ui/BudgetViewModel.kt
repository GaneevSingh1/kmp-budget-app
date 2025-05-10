package com.budget.project.ui

import androidx.lifecycle.ViewModel

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

val budget1 = Budget(
    id = 1,
    name = "Housing",
    timeFrame = TimeFrame.MONTHLY,
    expenses = listOf(
        MoneyEntry(name = "Fuel", amount = 500.0, timeFrame = TimeFrame.MONTHLY, isEnabled = true),
        MoneyEntry(name = "Food", amount = 200.0, timeFrame = TimeFrame.MONTHLY, isEnabled = false)
    ),
    incomes = listOf(
        MoneyEntry(name = "EROAD", amount = 500.0, timeFrame = TimeFrame.MONTHLY, isEnabled = true)
    ),
    savings = listOf(
        MoneyEntry(name = "Car", amount = 300.0, timeFrame = TimeFrame.MONTHLY, isEnabled = true)
    )
)

class BudgetViewModel : ViewModel() {
    private val budgets = mapOf( Pair(0, budget1) )

    fun getBudgetNames(): List<String> = budgets.values.map { it.name }

    fun getBudgetIdFromName(budgetName: String): Int {
        return budgets.values.find { it.name == budgetName }?.id ?: throw IllegalArgumentException("Budget not found")
    }

    fun getBudget(budgetId: Int): Budget {
        return budgets.values.find { it.id == budgetId } ?: throw IllegalArgumentException("Budget not found")
    }
}