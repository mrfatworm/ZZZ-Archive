/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root


import MainDispatcherRule
import feature.setting.data.FakeSettingRepository
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MainContainerViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var settingsRepository = FakeSettingRepository()
    private lateinit var viewModel: MainContainerViewModel

    @BeforeTest
    fun setup() {
        viewModel = MainContainerViewModel(settingsRepository)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        assertTrue(isDark)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.setIsDarkTheme(false)
        val state = viewModel.isDark.value
        assertFalse(state)
    }
}