package dev.ciox.rally.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import dev.ciox.rally.ui.bills.BillsScreen
import dev.ciox.rally.ui.overview.OverviewScreen

interface RallyDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

/**
 * Rally app navigation destinations
 */
object Overview : RallyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "overview"
    override val screen: @Composable () -> Unit = { OverviewScreen() }
}

object Accounts : RallyDestination {
    override val icon = Icons.Filled.AttachMoney
    override val route = "accounts"
    override val screen: @Composable () -> Unit = { AccountsScreen() }
}

object Bills : RallyDestination {
    override val icon = Icons.Filled.MoneyOff
    override val route = "bills"
    override val screen: @Composable () -> Unit = { BillsScreen() }
}

object SingleAccount : RallyDestination {
    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
    override val icon = Icons.Filled.Money
    override val route = "single_account"
    override val screen: @Composable () -> Unit = { SingleAccountScreen() }
    const val accountTypeArg = "account_type"
}

// Screens to be displayed in the top RallyTabRow
val rallyTabRowScreens = listOf(Overview, Accounts, Bills)