/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.domain


import MainDispatcherRule
import android.content.Context
import io.mockk.mockk
import org.junit.Rule
import setting.FakeSettingRepository
import utils.AppActions
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SettingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockContext = mockk<Context>(relaxed = true)
    private val settingsRepository = FakeSettingRepository()
    private val appActions = AppActions(mockContext)
    private lateinit var viewModel: SettingViewModel

    @BeforeTest
    fun setup() {
        viewModel = SettingViewModel(settingsRepository, appActions)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        val state = viewModel.uiState.value
        assertTrue(isDark)
        assertEquals("en", state.language.project)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.setIsDarkTheme(false)
        val state = viewModel.isDark.value
        assertFalse(state)
    }

    @Test
    fun `Set Language`() {
        viewModel.setLanguage("zh")
        val state = viewModel.uiState.value
        assertEquals("zh", state.language.project)
    }

}