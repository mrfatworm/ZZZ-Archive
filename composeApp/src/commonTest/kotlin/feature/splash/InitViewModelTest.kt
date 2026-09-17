/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.splash

import MainDispatcherTest
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.FakeAppInfoUseCase
import feature.setting.domain.FakeLanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import root.InitViewModel

class InitViewModelTest : MainDispatcherTest() {
    // All three differ from `InitState`'s defaults, so the assertions fail if the ViewModel never
    // reads the preferences.
    private val preferencesRepository =
        FakePreferenceRepository(isDarkTheme = false, uiScale = 1.5f, fontScale = 0.8f)
    private val appInfoUseCase = FakeAppInfoUseCase()
    private val viewModel =
        InitViewModel(
            themeUseCase = ThemeUseCase(preferencesRepository),
            uiScaleUseCase = UiScaleUseCase(preferencesRepository),
            languageUseCase = FakeLanguageUseCase(),
            appInfoUseCase = appInfoUseCase
        )

    @Test
    fun `Init Data Success`() {
        val uiState = viewModel.uiState.value
        assertFalse(uiState.isDark)
        assertEquals(1.5f, uiState.uiScale)
        assertEquals(0.8f, uiState.fontScale)
        assertEquals(appInfoUseCase.getAppVersion(), uiState.appVersion)
    }
}
