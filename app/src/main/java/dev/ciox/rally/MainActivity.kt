package dev.ciox.rally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import dev.ciox.rally.ui.*
import dev.ciox.rally.ui.accounts.AccountsScreen
import dev.ciox.rally.ui.accounts.SingleAccountScreen
import dev.ciox.rally.ui.bills.BillsScreen
import dev.ciox.rally.ui.components.RallyTabRow
import dev.ciox.rally.ui.overview.OverviewScreen
import dev.ciox.rally.ui.theme.RallyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyTheme {
                RallyApp()
            }
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            rallyTabRowScreens.find { it.route == currentDestination?.route } ?: Overview
        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = rallyTabRowScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Overview.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = Overview.route) {
                    OverviewScreen(
                        onAccountClick = { accountType ->
                            navController.navigateToSingleAccount(accountType)
                        },
                        onClickSeeAllAccounts = { navController.navigateSingleTopTo(Accounts.route) },
                        onClickSeeAllBills = { navController.navigateSingleTopTo(Bills.route) }
                    )
                }
                composable(route = Accounts.route) {
                    AccountsScreen(
                        onAccountClick = { accountType ->
                            navController.navigateToSingleAccount(accountType)
                        }
                    )
                }
                composable(route = Bills.route) {
                    BillsScreen(
                    )
                }
                composable(
                    route = SingleAccount.routeWithArgs,
                    arguments = SingleAccount.arguments,
                    deepLinks = SingleAccount.deepLinks
                ) { navBackStackEntry ->
                    val accountType =
                        navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
                    SingleAccountScreen(accountType)
                }
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/${accountType}")
}