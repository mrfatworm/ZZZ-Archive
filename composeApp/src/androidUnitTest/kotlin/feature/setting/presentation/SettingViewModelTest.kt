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
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Ignore
import org.junit.Rule
import utils.AppActionsUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

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
        every { themeUseCase.getPreferenceIsDarkTheme() } returns flowOf(true)
        coEvery { themeUseCase.setPreferenceIsDarkTheme(any()) } returns Unit
        every { appInfoUseCase.getAppVersion() } returns "Luciana 2024.11.13"
        every { appActionsUseCase.restart() } returns Unit
        every { uiScaleUseCase.getUiScale() } returns flowOf(1f)
        every { uiScaleUseCase.getFontScale() } returns flowOf(1f)
        coEvery { uiScaleUseCase.setUiScale(any()) } returns Unit
        coEvery { uiScaleUseCase.setFontScale(any()) } returns Unit

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
        viewModel.uiState.onEach { state ->
            assertEquals(1f, state.uiScale)
            assertEquals(1f, state.fontScale)
            assertEquals("en", state.language.code)
            assertEquals("Luciana 2024.11.13", state.appVersion)
        }
    }

    @Test
    fun `Set Dark Theme`() = runTest {
        viewModel.onAction(SettingAction.ChangeToDarkTheme(false))
        coVerify { themeUseCase.setPreferenceIsDarkTheme(false) }
    }

    @Test
    fun `Set Ui Scale`() = runTest {
        viewModel.onAction(SettingAction.ScaleUi(1.1f, 1.3f))
        coVerify { uiScaleUseCase.setUiScale(1.1f) }
        coVerify { uiScaleUseCase.setFontScale(1.3f) }
    }

    @Test
    fun `Restart App`() = runTest {
        viewModel.onAction(SettingAction.RestartApp)
        verify { appActionsUseCase.restart() }
    }


    @Ignore("Issue: kotlinx.coroutines.test.UncaughtExceptionsBeforeTest")
    @Test
    fun `Set Language`() = runTest {
        viewModel.onAction(SettingAction.ChangeLanguage("zh"))
        val state = viewModel.uiState.value
        assertEquals("zh", state.language.code)
    }
}