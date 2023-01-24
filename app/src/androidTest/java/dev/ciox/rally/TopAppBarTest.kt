package dev.ciox.rally

import androidx.compose.ui.test.junit4.createComposeRule
import dev.ciox.rally.ui.Overview
import dev.ciox.rally.ui.RallyDestination
import dev.ciox.rally.ui.components.RallyTabRow
import dev.ciox.rally.ui.rallyTabRowScreens
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        composeTestRule.setContent {
            RallyTabRow(allScreens = rallyTabRowScreens, onTabSelected = {}, currentScreen =)
        }
    }
}