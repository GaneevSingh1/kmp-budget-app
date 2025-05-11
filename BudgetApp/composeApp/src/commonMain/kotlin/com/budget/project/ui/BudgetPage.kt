package com.budget.project.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BudgetPage(
    budgetId: Int,
    onBackPressed: () -> Unit,
    viewModel: BudgetViewModel
){
    val budget = viewModel.getBudget(budgetId)
    val scrollState = rememberScrollState()

    Scaffold (
        topBar = { BudgetTopBar(budget.name, onBackPressed) },
    ) { padding ->
        Column (
            modifier = Modifier
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            BudgetContent(
                budgetTimeFrame = budget.timeFrame,
                incomes = budget.incomes,
                expenses = budget.expenses,
                savings = budget.savings
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetTopBar(
    budgetName: String,
    onBackPressed: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = budgetName,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back Button",
                )
            }
        }
    )
}

@Composable
fun BudgetContent(
    budgetTimeFrame: TimeFrame,
    incomes: List<MoneyEntry>,
    expenses: List<MoneyEntry>,
    savings: List<MoneyEntry>,
) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PieChart()

            Column {
                TimeFrameDropDown(budgetTimeFrame)
                PieChartKeys()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MoneyEntryColumn(
                title = "Income:",
                moneyEntries = incomes
            )

            Spacer(modifier = Modifier.height(20.dp))

            MoneyEntryColumn(
                title = "Expenses:",
                moneyEntries = expenses
            )

            Spacer(modifier = Modifier.height(20.dp))

            MoneyEntryColumn(
                title = "Savings:",
                moneyEntries = savings
            )
        }
    }
}

@Composable
fun MoneyEntryColumn(
    title: String,
    moneyEntries: List<MoneyEntry>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = "Add")
    }

    Spacer(modifier = Modifier.height(10.dp))

    moneyEntries.forEach { moneyEntry ->
        MoneyEntry(
            name = moneyEntry.name,
            amount = moneyEntry.amount
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun MoneyEntry(
    name: String,
    amount: Double
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = name)
        TextField(
            value = amount.toString(),
            onValueChange = { value -> println(value) },
            prefix = { Text("$") }
        )
    }
}

@Composable
fun PieChartKeys() {
    PieChartKey(
        color = Color.Red,
        key = "Red"
    )
    PieChartKey(
        color = Color.Blue,
        key = "Blue"
    )
    PieChartKey(
        color = Color.Green,
        key = "Green"
    )
}

@Composable
fun PieChartKey(
    color: Color,
    key: String
) {
    Row {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Back Button",
            tint = color
        )
        Text(text = key)
    }
}

@Composable
fun PieChart() {
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawArc(
                color = Color.Red,
                startAngle = 0f,
                sweepAngle = 90f,
                useCenter = true,
            )
            drawArc(
                color = Color.Blue,
                startAngle = 90f,
                sweepAngle = 90f,
                useCenter = true,
            )
            drawArc(
                color = Color.Green,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = true,
            )
        })
}
