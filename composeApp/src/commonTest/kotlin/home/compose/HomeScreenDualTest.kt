/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.compose

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import home.model.stubHomeState
import kotlin.test.Test

// Android UI Test is not supported yet
@OptIn(ExperimentalTestApi::class)
class HomeScreenDualTest {

    @Test
    fun showUrl() = runComposeUiTest {
        setContent {
            HomeScreenDual(uiState = stubHomeState)
        }
        onNodeWithText("Home List").assertExists()
    }
}