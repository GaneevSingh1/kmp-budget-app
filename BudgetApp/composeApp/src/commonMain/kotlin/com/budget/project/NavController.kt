package com.budget.project

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

interface Destination

@Serializable
object DashboardRoute: Destination

@Composable
fun MainNavigator(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = DashboardRoute,
    ) {
        composable<DashboardRoute> {
            DashboardPage()
        }
    }
}