/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.domain

import feature.banner.data.FakeBannerRepository
import feature.banner.data.stubBannerResponse
import feature.setting.data.FakeSystemConfigRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BannerUseCaseTest {

    private val bannerRepository = FakeBannerRepository()
    private val systemConfigRepository = FakeSystemConfigRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val bannerUseCase =
        BannerUseCase(bannerRepository, systemConfigRepository, languageUseCase)

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
        val result = bannerUseCase.getBannerIgnoreId().first()
        assertEquals(result, 1)
    }

    @Test
    fun `Set ignore id`() = runTest {
        systemConfigRepository.setBannerIgnoreId(2)
        val result = bannerUseCase.getBannerIgnoreId().first()
        assertEquals(result, 2)
    }
}