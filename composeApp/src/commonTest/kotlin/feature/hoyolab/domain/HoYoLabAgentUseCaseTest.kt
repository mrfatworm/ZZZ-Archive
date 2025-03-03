/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.FakeZzzCrypto
import feature.hoyolab.data.database.FakeHoYoLabAccountDao
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabAgentRepository
import feature.hoyolab.model.my_agent_detail.stubMyAgentDetailListItem
import feature.hoyolab.model.stubMyAgentsList
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HoYoLabAgentUseCaseTest {
    private val repository = FakeHoYoLabAgentRepository()
    private val accountDao = FakeHoYoLabAccountDao()
    private val preferencesRepository = FakePreferenceRepository()
    private val zzzCrypto = FakeZzzCrypto()
    private val languageUseCase = FakeLanguageUseCase()
    private val useCase = HoYoLabAgentUseCase(
        repository, accountDao, preferencesRepository, zzzCrypto, languageUseCase
    )

    @BeforeTest
    fun `Insert account to database`() = runTest {
        accountDao.insertAccount(stubHoYoLabAccountEntity.copy(uid = 1300051361))
        preferencesRepository.setDefaultHoYoLabAccountUid(1300051361)
    }

    @Test
    fun `Get agents list THEN success`() = runTest {
        val result = useCase.getAgentsList()
        assertEquals(Result.success(stubMyAgentsList), result)
    }

    @Test
    fun `Get agent detail THEN success`() = runTest {
        val result = useCase.getAgentDetail(1)
        assertEquals(Result.success(stubMyAgentDetailListItem), result)
    }
}