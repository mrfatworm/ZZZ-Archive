/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root

import MainDispatcherTest
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.ThemeUseCase
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first

class MainContainerViewModelTest : MainDispatcherTest() {
    // The ViewModel starts out dark, so the stored preference has to be light for the assertion
    // below to prove that `onStart` actually observed it.
    private val preferencesRepository = FakePreferenceRepository(isDarkTheme = false)
    private val themeUseCase = ThemeUseCase(preferencesRepository)
    private val viewModel = MainContainerViewModel(themeUseCase)

    @Test
    fun `Init Data Success`() = runViewModelTest {
        val isDark = viewModel.isDark.first()
        assertFalse(isDark)
    }

    @Test
    fun `Set Dark Theme`() = runViewModelTest {
        viewModel.setIsDarkTheme(true)
        assertTrue(preferencesRepository.getIsDarkTheme().first())
    }
}
