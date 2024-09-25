/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.compose

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import app.home.HomeScreenDual
import app.home.model.sampleHomeState
import kotlin.test.Test

// Android UI Test is not supported yet
@OptIn(ExperimentalTestApi::class)
class HomeScreenDualTest {

    @Test
    fun showUrl() = runComposeUiTest {
        setContent {
            HomeScreenDual(uiState = sampleHomeState)
        }
        onNodeWithText("Home List").assertExists()
    }
}