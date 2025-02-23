/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.database.HoYoLabAccountDao
import feature.hoyolab.data.repository.HoYoLabConfigRepository
import feature.hoyolab.model.GameRecordData
import feature.hoyolab.model.SignResponse
import feature.setting.data.PreferencesRepository
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GameRecordUseCase(
    private val hoYoLabConfigRepository: HoYoLabConfigRepository,
    private val accountDao: HoYoLabAccountDao,
    private val preferencesRepository: PreferencesRepository,
    private val zzzCrypto: ZzzCrypto,
    private val languageUseCase: LanguageUseCase
) {
    private suspend fun getGameRecord(): Result<GameRecordData> {
        val defaultAccountUid = preferencesRepository.getDefaultHoYoLabAccountUid().first()
        val account = accountDao.getAccount(defaultAccountUid).filterNotNull().first()
        val region = account.region
        val lToken = zzzCrypto.decryptData(account.lToken)
        val ltUid = zzzCrypto.decryptData(account.ltUid)
        val uid = account.uid
        val result = hoYoLabConfigRepository.requestGameRecord(uid, region, lToken, ltUid)
        result.fold(onSuccess = {
            return Result.success(it.data)
        }, onFailure = {
            return Result.failure(it)
        })
    }

    fun getGameRecordPeriodically(perMinutes: Int): Flow<Result<GameRecordData>> = flow {
        while (true) {
            emit(getGameRecord())
            delay(perMinutes * 60 * 1000L)
        }
    }

    suspend fun sign(): Result<SignResponse> {
        val defaultAccountUid = preferencesRepository.getDefaultHoYoLabAccountUid().first()
        val account = accountDao.getAccount(defaultAccountUid).filterNotNull()
        val languageCode = languageUseCase.getLanguage().first().officialCode
        val lToken = zzzCrypto.decryptData(account.first().lToken)
        val ltUid = zzzCrypto.decryptData(account.first().ltUid)
        val result = hoYoLabConfigRepository.requestSign(languageCode, lToken, ltUid)
        result.fold(onSuccess = {
            return Result.success(it)
        }, onFailure = {
            return Result.failure(it)
        })
    }

    fun getDefaultUid() = preferencesRepository.getDefaultHoYoLabAccountUid()

    fun getDefaultHoYoLabAccount(uid: Int) = accountDao.getAccount(uid)
}