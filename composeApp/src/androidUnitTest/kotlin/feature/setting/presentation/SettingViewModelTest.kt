/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation


import MainDispatcherRule
import android.content.Context
import database.UpdateDatabaseUseCase
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import utils.AppActionsUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SettingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockContext = mockk<Context>(relaxed = true)
    private val themeUseCase = mockk<ThemeUseCase>()
    private val uiScaleUseCase = mockk<UiScaleUseCase>()
    private val languageUseCase = mockk<LanguageUseCase>()
    private val appInfoUseCase = mockk<AppInfoUseCase>()
    private val appActionsUseCase = AppActionsUseCase(mockContext)
    private val updateDatabaseUseCase = mockk<UpdateDatabaseUseCase>()
    private lateinit var viewModel: SettingViewModel

    @BeforeTest
    fun setup() {
        every { languageUseCase.getLanguage().code } returns "en"
        every { languageUseCase.setLanguage(any()) } returns Unit
        every { themeUseCase.getPreferenceIsDarkTheme() } returns true
        every { themeUseCase.setPreferenceIsDarkTheme(any()) } returns Unit
        every { appInfoUseCase.getAppVersion() } returns "Luciana 2024.11.13"
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
        viewModel.setIsDarkTheme(false)
        verify { themeUseCase.setPreferenceIsDarkTheme(false) }
    }

    @Test
    fun `Set Ui Scale`() {
        viewModel.setUiScale(2f)
        verify { uiScaleUseCase.setUiScale(2f) }
    }

    @Test
    fun `Set Font Scale`() {
        viewModel.setFontScale(2f)
        verify { uiScaleUseCase.setFontScale(2f) }
    }

//    Issue: kotlinx.coroutines.test.UncaughtExceptionsBeforeTest
//    @Test
//    fun `Set Language`() {
//        viewModel.setLanguage("zh")
//        verify { languageUseCase.setLanguage("zh") }
//    }
}