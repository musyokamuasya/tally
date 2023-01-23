package dev.ciox.rally.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ciox.rally.navigateSingleTopTo
import dev.ciox.rally.navigateToSingleAccount
import dev.ciox.rally.ui.Accounts
import dev.ciox.rally.ui.Bills
import dev.ciox.rally.ui.Overview
import dev.ciox.rally.ui.SingleAccount
import dev.ciox.rally.ui.accounts.AccountsScreen
import dev.ciox.rally.ui.accounts.SingleAccountScreen
import dev.ciox.rally.ui.bills.BillsScreen
import dev.ciox.rally.ui.overview.OverviewScreen

@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
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