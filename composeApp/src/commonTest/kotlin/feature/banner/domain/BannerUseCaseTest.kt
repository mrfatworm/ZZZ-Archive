/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.domain

import feature.banner.data.FakeBannerRepository
import feature.banner.data.stubBannerResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BannerUseCaseTest {

    private val bannerRepository = FakeBannerRepository()
    private val bannerUseCase = BannerUseCase(bannerRepository)

    @Test
    fun `Get Banner Success`() = runTest {
        val result = bannerUseCase.invoke().getOrNull()
        assertEquals(result, stubBannerResponse)
    }

    @Test
    fun `Get Banner Error`() = runTest {
        bannerRepository.setError(true)
        val result = bannerUseCase.invoke().getOrNull()
        assertNull(result)
    }
}