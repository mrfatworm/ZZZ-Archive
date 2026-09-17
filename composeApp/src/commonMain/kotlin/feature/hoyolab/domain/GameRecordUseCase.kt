/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.credential.HoYoLabCredentialStore
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
    private val credentialStore: HoYoLabCredentialStore,
    private val languageUseCase: LanguageUseCase
) {
    private suspend fun getGameRecord(): Result<GameRecordData> {
        val defaultAccountUid = preferencesRepository.getDefaultHoYoLabAccountUid().first()
        val account = accountDao.getAccount(defaultAccountUid).filterNotNull().first()
        val region = account.region
        val uid = account.uid
        // Without a credential the row can never be synced again, so it is dropped rather than
        // left behind as an account that silently fails on every poll.
        val credential = credentialStore.read(uid) ?: run {
            accountDao.deleteAccount(uid)
            preferencesRepository.setDefaultHoYoLabAccountUid(0)
            return Result.failure(MissingHoYoLabCredentialException(uid))
        }
        hoYoLabConfigRepository.requestGameRecord(
            uid = uid,
            region = region,
            lToken = credential.lToken,
            ltUid = credential.ltUid
        ).fold(onSuccess = {
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
        val account = accountDao.getAccount(defaultAccountUid).filterNotNull().first()
        val languageCode = languageUseCase.getLanguage().first().officialCode
        val credential =
            credentialStore.read(account.uid) ?: return Result.failure(MissingHoYoLabCredentialException(account.uid))
        val result =
            hoYoLabConfigRepository.requestSign(
                languageCode = languageCode,
                lToken = credential.lToken,
                ltUid = credential.ltUid
            )
        result.fold(onSuccess = {
            return Result.success(it)
        }, onFailure = {
            return Result.failure(it)
        })
    }

    fun getDefaultUid() = preferencesRepository.getDefaultHoYoLabAccountUid()

    fun getDefaultHoYoLabAccount(uid: Int) = accountDao.getAccount(uid)
}
