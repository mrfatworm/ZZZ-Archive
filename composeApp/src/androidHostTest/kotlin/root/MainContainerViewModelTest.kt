/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root

import MainDispatcherRule
import feature.setting.domain.ThemeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule

class MainContainerViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val themeUseCase = mockk<ThemeUseCase>()
    private lateinit var viewModel: MainContainerViewModel

    @BeforeTest
    fun setup() {
        every { themeUseCase.getPreferenceIsDarkTheme() } returns flowOf(true)
        coEvery { themeUseCase.setPreferenceIsDarkTheme(any()) } returns Unit
        viewModel = MainContainerViewModel(themeUseCase)
    }

    @Test
    fun `Init Data Success`() = runTest {
        val isDark = viewModel.isDark.first()
        assertTrue(isDark)
    }

    @Test
    fun `Set Light Theme`() = runTest {
        viewModel.setIsDarkTheme(false)
        coVerify { themeUseCase.setPreferenceIsDarkTheme(false) }
    }
}
