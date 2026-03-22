/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.splash

import MainDispatcherRule
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import root.InitViewModel
import utils.Language

class InitViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val themeUseCase = mockk<ThemeUseCase>()
    private val uiScaleUseCase = mockk<UiScaleUseCase>()
    private val languageUseCase = mockk<LanguageUseCase>()
    private val appInfoUseCase = mockk<AppInfoUseCase>()
    private lateinit var viewModel: InitViewModel

    @BeforeTest
    fun setup() {
        every { themeUseCase.getPreferenceIsDarkTheme() } returns flowOf(true)
        every { languageUseCase.getLanguage() } returns flowOf(Language.English)
        every { appInfoUseCase.getAppVersion() } returns "Luciana 2024.11.13"
        every { uiScaleUseCase.getUiScale() } returns flowOf(1f)
        every { uiScaleUseCase.getFontScale() } returns flowOf(1f)

        viewModel =
            InitViewModel(
                themeUseCase = themeUseCase,
                uiScaleUseCase = uiScaleUseCase,
                languageUseCase = languageUseCase,
                appInfoUseCase = appInfoUseCase
            )
    }

    @Test
    fun `Init Data Success`() {
        val uiState = viewModel.uiState.value
        assertTrue(uiState.isDark)
        assertEquals(1f, uiState.uiScale)
        assertEquals(1f, uiState.fontScale)
        assertEquals("Luciana 2024.11.13", uiState.appVersion)
    }
}
