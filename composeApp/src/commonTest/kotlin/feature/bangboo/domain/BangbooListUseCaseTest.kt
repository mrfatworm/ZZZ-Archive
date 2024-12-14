/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.repository.FakeBangbooRepository
import feature.bangboo.model.stubBangbooList
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import utils.AgentAttribute
import utils.ZzzRarity
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class BangbooListUseCaseTest {

    private val bangbooRepository = FakeBangbooRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val bangbooListUseCase = BangbooListUseCase(bangbooRepository, languageUseCase)


    @Test
    fun `Get bangboo list success`() = runTest {
        val result = bangbooListUseCase.invoke().first()
        assertEquals(result, stubBangbooList)
    }

    @Test
    fun `Request bangboo list success`() = runTest {
        val result = bangbooListUseCase.updateBangbooList().getOrNull()
        assertEquals(result, Unit)
    }

    @Test
    fun `Request bangboo list error`() = runTest {
        bangbooRepository.setError(true)
        val result = bangbooListUseCase.updateBangbooList().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Filter default`() {
        val result = bangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooList,
            selectedRarities = emptySet(),
            selectedAttributes = emptySet(),
        )
        assertEquals(result.size, 2)

    }

    @Test
    fun `Filter Penguinboo`() {
        val result = bangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooList,
            selectedRarities = setOf(ZzzRarity.RANK_A),
            selectedAttributes = setOf(AgentAttribute.Ice),
        )
        assertEquals(result.first().name, "企鵝布")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter Butler`() {
        val result = bangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooList,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedAttributes = setOf(AgentAttribute.Physical)
        )
        assertEquals(result.first().name, "巴特勒")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter not match`() {
        val result = bangbooListUseCase.filterBangbooList(
            bangbooList = stubBangbooList,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedAttributes = setOf(AgentAttribute.Ether)
        )
        assertEquals(result, emptyList())
    }
}