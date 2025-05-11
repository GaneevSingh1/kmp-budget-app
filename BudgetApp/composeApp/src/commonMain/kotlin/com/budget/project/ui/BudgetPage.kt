package com.budget.project.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val pieChartColors = listOf(Color.Red, Color.Blue, Color.Green, Color.Cyan, Color.LightGray)

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
            horizontalArrangement = Arrangement.End
        ){
            TimeFrameDropDown(budgetTimeFrame)
        }

        PieChartRow(
            data = expenses.plus(savings).associate { Pair(it.name, it.amount.toFloat()) }
        )

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
fun PieChartRow(
    data: Map<String, Float>
) {
    val sortedData = data.entries.sortedByDescending { it.value }
    val topEntries = sortedData.take(4)
    val otherEntries = sortedData.drop(4)
    val otherTotal = otherEntries.sumOf { it.value.toDouble() }.toFloat()

    val chartMap = mutableMapOf<Color, Float>()
    val keyMap = mutableMapOf<Color, String>()

    topEntries.forEachIndexed { index, entry ->
        val color = pieChartColors[index]
        chartMap[color] = entry.value
        keyMap[color] = entry.key
    }

    if (otherTotal > 0f) {
        val otherColor = pieChartColors.getOrElse(4) { Color.Gray }
        chartMap[otherColor] = otherTotal
        keyMap[otherColor] = "Other"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PieChart(chartMap)

        Column {
            PieChartKeys(keyMap)
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
fun PieChartKeys(
    data: Map<Color, String>
) {
    data.forEach {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Back Button",
                tint = it.key
            )
            Text(text = it.value)
        }
    }
}

@Composable
fun PieChart(
    data: Map<Color, Float>,
    modifier: Modifier = Modifier.size(200.dp)
) {
    val totalAmount = data.values.sum()
    val normalizedData = data.mapValues { (_, amount) ->
        amount / totalAmount
    }

    Canvas(modifier = modifier) {
        var startAngle = -90f // Start from the top
        val diameter = size.minDimension
        val arcSize = Rect(0f, 0f, diameter, diameter)

        normalizedData.forEach { (color, percentage) ->
            val sweepAngle = percentage * 360f
            drawArc(
                color = color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = arcSize.topLeft,
                size = arcSize.size
            )
            startAngle += sweepAngle
        }
    }
}
