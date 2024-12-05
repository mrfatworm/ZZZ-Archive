/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation


import MainDispatcherRule
import database.UpdateDatabaseUseCase
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.FakeLanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Ignore
import org.junit.Rule
import utils.AppActionsUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SettingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val themeUseCase = mockk<ThemeUseCase>()
    private val uiScaleUseCase = mockk<UiScaleUseCase>()
    private val languageUseCase = FakeLanguageUseCase()
    private val appInfoUseCase = mockk<AppInfoUseCase>()
    private val appActionsUseCase = mockk<AppActionsUseCase>()
    private val updateDatabaseUseCase = mockk<UpdateDatabaseUseCase>()
    private lateinit var viewModel: SettingViewModel

    @BeforeTest
    fun setup() {
        every { themeUseCase.getPreferenceIsDarkTheme() } returns true
        every { themeUseCase.setPreferenceIsDarkTheme(any()) } returns Unit
        every { appInfoUseCase.getAppVersion() } returns "Luciana 2024.11.13"
        every { appActionsUseCase.restart() } returns Unit
        every { uiScaleUseCase.getUiScale() } returns 1f
        every { uiScaleUseCase.getFontScale() } returns 1f
        every { uiScaleUseCase.setUiScale(any()) } returns Unit
        every { uiScaleUseCase.setFontScale(any()) } returns Unit

        viewModel =
            SettingViewModel(
                themeUseCase,
                uiScaleUseCase,
                appInfoUseCase,
                appActionsUseCase,
                languageUseCase,
                updateDatabaseUseCase
            )
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        val state = viewModel.uiState.value
        assertTrue(isDark)
        assertEquals(1f, state.uiScale)
        assertEquals(1f, state.fontScale)
        assertEquals("en", state.language.code)
        assertEquals("Luciana 2024.11.13", state.appVersion)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.onAction(SettingAction.ChangeToDarkTheme(false))
        verify { themeUseCase.setPreferenceIsDarkTheme(false) }
    }

    @Test
    fun `Set Ui Scale`() {
        viewModel.onAction(SettingAction.ScaleUi(1.1f, 1.3f))
        verify { uiScaleUseCase.setUiScale(1.1f) }
        verify { uiScaleUseCase.setFontScale(1.3f) }
    }

    @Test
    fun `Restart App`() {
        viewModel.onAction(SettingAction.RestartApp)
        verify { appActionsUseCase.restart() }
    }


    @Ignore("Issue: kotlinx.coroutines.test.UncaughtExceptionsBeforeTest")
    @Test
    fun `Set Language`() {
        viewModel.onAction(SettingAction.ChangeLanguage("zh"))
        val state = viewModel.uiState.value
        assertEquals("zh", state.language.code)
    }
}