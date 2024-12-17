/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.FakeZzzCrypto
import feature.hoyolab.data.database.FakeHoYoLabAccountDao
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabRepository
import feature.hoyolab.model.stubGameRecordResponse
import feature.hoyolab.model.stubSignResponse
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GameRecordUseCaseTest {
    private val hoYoLabRepository = FakeHoYoLabRepository()
    private val accountDao = FakeHoYoLabAccountDao()
    private val preferencesRepository = FakePreferenceRepository()
    private val zzzCrypto = FakeZzzCrypto()
    private val languageUseCase = FakeLanguageUseCase()
    private val useCase = GameRecordUseCase(
        hoYoLabRepository, accountDao, preferencesRepository, zzzCrypto, languageUseCase
    )

    @BeforeTest
    fun `Insert account to database`() = runTest {
        accountDao.insertAccount(stubHoYoLabAccountEntity.copy(uid = 1300051361))
        preferencesRepository.setDefaultHoYoLabAccountUid(1300051361)
    }

    @Test
    fun `Get game record periodically success`() = runTest {
        val result = useCase.getGameRecordPeriodically(1).first()
        assertEquals(Result.success(stubGameRecordResponse.data), result)
    }

    @Test
    fun `Sign success`() = runTest {
        val result = useCase.sign()
        assertEquals(Result.success(stubSignResponse), result)
    }

    @Test
    fun `Get default uid success`() = runTest {
        val result = useCase.getDefaultUid().first()
        assertEquals(1300051361, result)
    }

    @Test
    fun `Get default ho yo lab account success`() = runTest {
        val result = useCase.getDefaultHoYoLabAccount(1300051361).first()
        assertEquals(1300051361, result?.uid)
    }
}