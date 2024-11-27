/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.domain

import feature.banner.data.FakeBannerRepository
import feature.banner.data.stubBannerResponse
import feature.setting.data.FakeSettingRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BannerUseCaseTest {

    private val bannerRepository = FakeBannerRepository()
    private val settingRepository = FakeSettingRepository()
    private val bannerUseCase = BannerUseCase(bannerRepository, settingRepository)

    @Test
    fun `Get banner success`() = runTest {
        val result = bannerUseCase.invoke().getOrNull()
        assertEquals(result, stubBannerResponse)
    }

    @Test
    fun `Get banner error`() = runTest {
        bannerRepository.setError(true)
        val result = bannerUseCase.invoke().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get ignore id`() = runTest {
        val result = bannerUseCase.getBannerIgnoreId()
        assertEquals(result, 0)
    }

    @Test
    fun `Set ignore id`() = runTest {
        settingRepository.setBannerIgnoreId(1)
        val result = bannerUseCase.getBannerIgnoreId()
        assertEquals(result, 1)
    }
}