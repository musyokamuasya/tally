package dev.ciox.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.testing.TestNavHostController
import dev.ciox.rally.ui.components.RallyNavHost
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get: Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setUpRallyNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)

            navController.navigatorProvider.addNavigator(ComposeNavigator())

            RallyNavHost(navController = navController)
        }
    }

    @Test
    fun rallyNavHost_verifyStartDestination() {
        composeTestRule.onNodeWithContentDescription("Overview Screen").assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_clickAllAccounts_navigatesToAllAccounts() {
        composeTestRule.onNodeWithContentDescription("All Accounts").performClick()
        composeTestRule.onNodeWithContentDescription("Accounts Screen").assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_clickAllBills_navigatesToAllBills() {
        composeTestRule.onNodeWithContentDescription("All Bills")
            .performScrollTo()
            .performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "bills")

    }
}