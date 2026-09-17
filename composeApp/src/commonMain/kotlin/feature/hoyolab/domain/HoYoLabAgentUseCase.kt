package feature.hoyolab.domain

import feature.hoyolab.data.credential.HoYoLabCredentialStore
import feature.hoyolab.data.database.HoYoLabAccountDao
import feature.hoyolab.data.repository.HoYoLabAgentRepository
import feature.hoyolab.model.MyAgentListItem
import feature.hoyolab.model.agent.MyAgentDetail
import feature.setting.data.PreferencesRepository
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

class HoYoLabAgentUseCase(
    private val repository: HoYoLabAgentRepository,
    private val accountDao: HoYoLabAccountDao,
    private val preferencesRepository: PreferencesRepository,
    private val credentialStore: HoYoLabCredentialStore,
    private val languageUseCase: LanguageUseCase
) {
    suspend fun getAgentsList(): Result<List<MyAgentListItem>> {
        val defaultAccountUid = preferencesRepository.getDefaultHoYoLabAccountUid().first()
        val account = accountDao.getAccount(defaultAccountUid).filterNotNull().first()
        val languageCode = languageUseCase.getLanguage().first().officialCode
        val region = account.region
        val uid = account.uid
        val credential = credentialStore.read(uid) ?: return Result.failure(MissingHoYoLabCredentialException(uid))
        val result = repository.requestPlayerAgentList(
            languageCode = languageCode,
            uid = uid,
            region = region,
            lToken = credential.lToken,
            ltUid = credential.ltUid
        )
        result.fold(onSuccess = {
            return Result.success(it)
        }, onFailure = {
            return Result.failure(it)
        })
    }

    suspend fun getAgentDetail(agentId: Int): Result<MyAgentDetail> {
        val defaultAccountUid = preferencesRepository.getDefaultHoYoLabAccountUid().first()
        val account = accountDao.getAccount(defaultAccountUid).filterNotNull().first()
        val languageCode = languageUseCase.getLanguage().first().officialCode
        val region = account.region
        val uid = account.uid
        val credential = credentialStore.read(uid) ?: return Result.failure(MissingHoYoLabCredentialException(uid))
        val result =
            repository.requestPlayerAgentDetail(
                languageCode = languageCode,
                uid = uid,
                region = region,
                lToken = credential.lToken,
                ltUid = credential.ltUid,
                agentId = agentId
            )
        result.fold(onSuccess = {
            return Result.success(it)
        }, onFailure = {
            return Result.failure(it)
        })
    }
}
