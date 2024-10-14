package com.socialdealapp.presentation.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable

sealed class NavigationItem(var route: String, var icon: @Composable () -> Unit, var title: String) {
    object DealsList : NavigationItem("deals", icon = {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "Home"
        )
    }, "Deals List")
    object FavoriteDealsScreen : NavigationItem("favorite_deals",icon = {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite"
        )
    }, "Favorite")
    object SettingsScreen : NavigationItem("settings_deals",icon = {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Settings"
        )
    }, "Settings")
    object DealsDesc: NavigationItem("deals_desc", icon = {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "Deals Description"
        )
    }, "Deals Desc")
}