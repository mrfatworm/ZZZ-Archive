/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root


import MainDispatcherRule
import feature.setting.domain.ThemeUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class MainContainerViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val themeUseCase = mockk<ThemeUseCase>()
    private lateinit var viewModel: MainContainerViewModel

    @BeforeTest
    fun setup() {
        every { themeUseCase.getIsDarkTheme() } returns true
        every { themeUseCase.setIsDarkTheme(any()) } returns Unit
        viewModel = MainContainerViewModel(themeUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        assertTrue(isDark)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.setIsDarkTheme(false)
        verify { themeUseCase.setIsDarkTheme(false) }
    }
}