package com.pidrop.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pidrop.app.R
import com.pidrop.app.ui.about.AboutScreen
import com.pidrop.app.ui.placeholder.RolePlaceholderScreen
import com.pidrop.app.ui.role.RoleSelectionScreen
import com.pidrop.app.ui.welcome.WelcomeScreen

private object Destinations {
    const val WELCOME = "welcome"
    const val ABOUT = "about"
    const val ROLE_SELECTION = "role_selection"
    const val CUSTOMER = "customer_entry"
    const val RIDER = "rider_entry"
}

@Composable
fun PidropNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.WELCOME) {
        composable(Destinations.WELCOME) {
            WelcomeScreen(
                onGetStarted = { navController.navigate(Destinations.ROLE_SELECTION) { launchSingleTop = true } },
                onAbout = { navController.navigate(Destinations.ABOUT) { launchSingleTop = true } },
            )
        }
        composable(Destinations.ABOUT) {
            AboutScreen(onBack = { navController.popBackStack() })
        }
        composable(Destinations.ROLE_SELECTION) {
            RoleSelectionScreen(
                onBack = { navController.popBackStack() },
                onCustomer = { navController.navigate(Destinations.CUSTOMER) { launchSingleTop = true } },
                onRider = { navController.navigate(Destinations.RIDER) { launchSingleTop = true } },
            )
        }
        composable(Destinations.CUSTOMER) {
            RolePlaceholderScreen(
                title = R.string.customer_placeholder_title,
                description = R.string.customer_placeholder_description,
                onBack = { navController.popBackStack() },
            )
        }
        composable(Destinations.RIDER) {
            RolePlaceholderScreen(
                title = R.string.rider_placeholder_title,
                description = R.string.rider_placeholder_description,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
