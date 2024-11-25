/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.splash


import MainDispatcherRule
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SplashViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val themeUseCase = mockk<ThemeUseCase>()
    private val languageUseCase = mockk<LanguageUseCase>()
    private val appInfoUseCase = mockk<AppInfoUseCase>()
    private lateinit var viewModel: SplashViewModel

    @BeforeTest
    fun setup() {
        every { themeUseCase.getIsDarkTheme() } returns true
        every { languageUseCase.getLanguage().code } returns "en"
        every { appInfoUseCase.getAppVersion() } returns "Luciana 2024.11.13"
        viewModel = SplashViewModel(themeUseCase, languageUseCase, appInfoUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        val appVersion = viewModel.appVersion.value
        assertTrue(isDark)
        assertEquals("Luciana 2024.11.13", appVersion)
    }
}