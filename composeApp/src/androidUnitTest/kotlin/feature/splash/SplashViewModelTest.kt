/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.splash


import MainDispatcherRule
import feature.setting.data.FakeAppInfoRepository
import feature.setting.data.FakeSettingRepository
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SplashViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var settingsRepository = FakeSettingRepository()
    private var appInfoRepository = FakeAppInfoRepository()
    private lateinit var viewModel: SplashViewModel

    @BeforeTest
    fun setup() {
        viewModel = SplashViewModel(settingsRepository, appInfoRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.isDark.value
        assertTrue(state)
    }
}