/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.splash


import MainDispatcherRule
import org.junit.Rule
import setting.FakeSettingRepository
import setting.SettingsRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SplashViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: SplashViewModel

    @BeforeTest
    fun setup() {
        settingsRepository = FakeSettingRepository()
        viewModel = SplashViewModel(settingsRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.isDark.value
        assertTrue(state)
    }
}