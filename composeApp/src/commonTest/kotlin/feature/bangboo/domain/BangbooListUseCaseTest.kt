/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.FakeBangbooRepository
import feature.bangboo.model.stubBangbooListResponse
import kotlinx.coroutines.test.runTest
import utils.AgentAttribute
import utils.ZzzRarity
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class BangbooListUseCaseTest {

    private val banRepository = FakeBangbooRepository()
    private val bangBangbooListUseCase = BangbooListUseCase(banRepository)


    @Test
    fun `Get Bangboo List Success`() = runTest {
        val result = bangBangbooListUseCase.invoke().getOrNull()
        assertEquals(result, stubBangbooListResponse.bangboo.reversed())
    }

    @Test
    fun `Get Bangboo List Fail`() = runTest {
        banRepository.setError(true)
        val result = bangBangbooListUseCase.invoke().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Filter Default`() {
        val result = bangBangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooListResponse.bangboo,
            selectedRarities = emptySet(),
            selectedAttributes = emptySet(),
        )
        assertEquals(result.size, 2)

    }

    @Test
    fun `Filter Penguinboo`() {
        val result = bangBangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooListResponse.bangboo,
            selectedRarities = setOf(ZzzRarity.RANK_A),
            selectedAttributes = setOf(AgentAttribute.Ice),
        )
        assertEquals(result.first().name, "企鵝布")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter Butler`() {
        val result = bangBangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooListResponse.bangboo,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedAttributes = setOf(AgentAttribute.Physical)
        )
        assertEquals(result.first().name, "巴特勒")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter Not Match`() {
        val result = bangBangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooListResponse.bangboo,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedAttributes = setOf(AgentAttribute.Ether)
        )
        assertEquals(result, emptyList())
    }
}