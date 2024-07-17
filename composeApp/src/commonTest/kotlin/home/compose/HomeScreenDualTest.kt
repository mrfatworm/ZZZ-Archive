/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package home.compose

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class HomeScreenDualTest {

    @Test
    fun showUrl() = runComposeUiTest {
        setContent {
            HomeScreenDual(viewModelText = "Haha")
        }
        onNodeWithText("Haha").assertExists()
    }
}