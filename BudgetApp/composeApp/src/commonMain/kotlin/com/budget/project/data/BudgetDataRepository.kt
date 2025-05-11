package com.budget.project.data

import com.budget.project.ui.Budget
import com.budget.project.ui.MoneyEntry
import com.budget.project.ui.TimeFrame

class BudgetDataRepository {
    private val budgets = listOf(budget1, budget2, budget3)

    fun getAllBudgets(): List<Budget> = budgets

    fun getAllBudgetNames(): List<String> = budgets.map { it.name }

    fun getBudgetById(id: Int): Budget {
        return budgets[id]
    }

    fun getBudgetIdByName(budgetName: String): Int {
        return budgets.find { it.name == budgetName }?.id ?: throw IllegalArgumentException("Budget not found")
    }
}

val budget1 = Budget(
    id = 0,
    name = "Budget 1",
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

val budget2 = Budget(
    id = 1,
    name = "Budget 2",
    timeFrame = TimeFrame.FORTNIGHTLY,
    expenses = listOf(
        MoneyEntry(name = "Fuel", amount = 500.0, timeFrame = TimeFrame.MONTHLY, isEnabled = true),
        MoneyEntry(name = "Food", amount = 200.0, timeFrame = TimeFrame.MONTHLY, isEnabled = false),
        MoneyEntry(name = "Drinks", amount = 300.0, timeFrame = TimeFrame.MONTHLY, isEnabled = false),
        MoneyEntry(name = "ran1", amount = 100.0, timeFrame = TimeFrame.MONTHLY, isEnabled = false),
        MoneyEntry(name = "rand2", amount = 100.0, timeFrame = TimeFrame.MONTHLY, isEnabled = false)
    ),
    incomes = listOf(
        MoneyEntry(name = "EROAD", amount = 500.0, timeFrame = TimeFrame.MONTHLY, isEnabled = true)
    ),
    savings = listOf(
        MoneyEntry(name = "Car", amount = 300.0, timeFrame = TimeFrame.MONTHLY, isEnabled = true)
    )
)

val budget3 = Budget(
    id = 2,
    name = "Budget 3",
    timeFrame = TimeFrame.WEEKLY,
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