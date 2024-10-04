/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui


import MainDispatcherRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Rule
import setting.FakeSettingRepository
import setting.SettingsRepository
import kotlin.test.BeforeTest
import kotlin.test.Test

class MainFunViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: MainFunViewModel

    @BeforeTest
    fun setup() {
        settingsRepository = FakeSettingRepository()
        viewModel = MainFunViewModel(settingsRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.isDark.value
        assertThat(state).isEqualTo(true)
    }
}