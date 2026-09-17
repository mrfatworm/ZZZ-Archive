/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import MainDispatcherTest
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.FakeAppInfoUseCase
import feature.setting.domain.FakeLanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import utils.FakeAppActionsUseCase

class SettingViewModelTest : MainDispatcherTest() {
    // The scales differ from `settingState`'s defaults, so the assertions fail if `onStart` never
    // observed them.
    private val preferencesRepository = FakePreferenceRepository(uiScale = 1.5f, fontScale = 0.8f)
    private val appInfoUseCase = FakeAppInfoUseCase()
    private val appActionsUseCase = FakeAppActionsUseCase()
    private val viewModel =
        SettingViewModel(
            ThemeUseCase(preferencesRepository),
            UiScaleUseCase(preferencesRepository),
            appInfoUseCase,
            appActionsUseCase,
            FakeLanguageUseCase()
        )

    @Test
    fun `Init Data Success`() = runViewModelTest {
        val state = viewModel.uiState.first()
        assertEquals(1.5f, state.uiScale)
        assertEquals(0.8f, state.fontScale)
        assertEquals("en", state.language.code)
        assertEquals(appInfoUseCase.getAppVersion(), state.appVersion)
    }

    @Test
    fun `Set Dark Theme`() = runViewModelTest {
        viewModel.onAction(SettingAction.ChangeToDarkTheme(false))
        assertEquals(false, preferencesRepository.getIsDarkTheme().first())
    }

    @Test
    fun `Set Ui Scale`() = runViewModelTest {
        viewModel.onAction(SettingAction.ScaleUi(1.1f, 1.3f))
        assertEquals(1.1f, preferencesRepository.getUiScale().first())
        assertEquals(1.3f, preferencesRepository.getFontScale().first())
    }

    @Test
    fun `Restart App`() = runViewModelTest {
        viewModel.onAction(SettingAction.RestartApp)
        assertEquals(1, appActionsUseCase.restartCount)
    }
}
