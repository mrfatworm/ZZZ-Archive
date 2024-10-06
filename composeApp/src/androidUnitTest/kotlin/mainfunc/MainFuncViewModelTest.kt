/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package mainfunc


import MainDispatcherRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import mainfunc.data.BannerRepository
import mainfunc.data.FakeBannerRepository
import mainfunc.model.stubBannerResponse
import org.junit.Rule
import setting.FakeSettingRepository
import setting.SettingsRepository
import kotlin.test.BeforeTest
import kotlin.test.Test

class MainFuncViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var bannerRepository: BannerRepository
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: MainFuncViewModel

    @BeforeTest
    fun setup() {
        bannerRepository = FakeBannerRepository()
        settingsRepository = FakeSettingRepository()
        viewModel = MainFuncViewModel(bannerRepository, settingsRepository)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        val banner = viewModel.banner.value
        assertThat(isDark).isEqualTo(true)
        assertThat(banner).isEqualTo(stubBannerResponse)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.setIsDarkTheme(false)
        val state = viewModel.isDark.value
        assertThat(state).isEqualTo(false)
    }

    @Test
    fun `Set Banner Ignore Id as One than ignore banner data`() {
        viewModel.setBannerIgnoreId(1)
        val state = viewModel.banner.value
        assertThat(state).isEqualTo(null)
    }
}