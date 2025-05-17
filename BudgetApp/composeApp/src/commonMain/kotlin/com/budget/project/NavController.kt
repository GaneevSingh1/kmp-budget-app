package com.budget.project

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.budget.project.ui.BudgetPage
import com.budget.project.ui.BudgetViewModel
import com.budget.project.ui.DashboardPage
import kotlinx.serialization.Serializable

interface Destination

@Serializable
object DashboardRoute: Destination

@Serializable
data class BudgetRoute(val budgetId: Int): Destination

@Composable
fun MainNavigator(
    navController: NavHostController = rememberNavController(),
    viewModel: BudgetViewModel
) {
    NavHost(
        navController = navController,
        startDestination = DashboardRoute,
    ) {
        composable<DashboardRoute> {
            DashboardPage(
                navigateToBudget = { budgetName ->
                    val budgetId = viewModel.getBudgetIdFromName(budgetName)
                    navController.navigate(BudgetRoute(budgetId))
                },
                viewModel
            )
        }
        composable<BudgetRoute> { backStackEntry ->
            val budgetRoute: BudgetRoute = backStackEntry.toRoute()
            BudgetPage(
                budgetId = budgetRoute.budgetId,
                onBackPressed = {
                    navController.navigateUp()
                },
                viewModel
            )
        }
    }
}