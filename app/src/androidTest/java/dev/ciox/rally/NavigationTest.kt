package dev.ciox.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dev.ciox.rally.ui.components.RallyNavHost
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get: Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun rallyNavHost(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)

            navController.navigatorProvider.addNavigator(ComposeNavigator())

            RallyNavHost(navController = navController)
        }
        fail()
    }
}