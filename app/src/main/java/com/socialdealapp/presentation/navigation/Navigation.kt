package com.socialdealapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.socialdealapp.presentation.screen.deals_details_screen.DealsDetailsScreen
import com.socialdealapp.presentation.screen.deals_screen.DealsRouter
import com.socialdealapp.presentation.screen.favorite_screen.FavoriteDealsScreen
import com.socialdealapp.presentation.screen.settings_screen.SettingsScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController, startDestination = NavigationItem.DealsList.route) {
        composable(NavigationItem.DealsList.route) {
            DealsRouter(
                onNavigate = { dealId ->
                    navController.navigate(
                        "${NavigationItem.DealsDesc.route}/$dealId"
                    )
                }
            )
        }
        composable(
            "${NavigationItem.DealsDesc.route}/{dealId}",
            arguments = listOf(navArgument("dealId") { type = NavType.StringType })
        ) {
            val dealId = it.arguments?.getString("dealId") ?: ""
            DealsDetailsScreen(
                dealId = dealId,
                onBackPressed = { navController.navigateUp() })
        }
        composable(NavigationItem.FavoriteDealsScreen.route) {
            FavoriteDealsScreen(
                onBackPressed = { navController.navigateUp() }
            )
        }
        composable(NavigationItem.SettingsScreen.route) {
            SettingsScreen(
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}