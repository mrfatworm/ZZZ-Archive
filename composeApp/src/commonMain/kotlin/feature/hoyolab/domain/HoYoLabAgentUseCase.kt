package feature.hoyolab.domain

import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.database.HoYoLabAccountDao
import feature.hoyolab.data.repository.HoYoLabAgentRepository
import feature.hoyolab.model.MyAgentListItem
import feature.setting.data.PreferencesRepository
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

class HoYoLabAgentUseCase(
    private val repository: HoYoLabAgentRepository,
    private val accountDao: HoYoLabAccountDao,
    private val preferencesRepository: PreferencesRepository,
    private val zzzCrypto: ZzzCrypto,
    private val languageUseCase: LanguageUseCase
) {

    suspend fun getAgentsList(): Result<List<MyAgentListItem>> {
        val defaultAccountUid = preferencesRepository.getDefaultHoYoLabAccountUid().first()
        val account = accountDao.getAccount(defaultAccountUid).filterNotNull().first()
        val languageCode = languageUseCase.getLanguage().first().officialCode
        val region = account.region
        val lToken = zzzCrypto.decryptData(account.lToken)
        val ltUid = zzzCrypto.decryptData(account.ltUid)
        val uid = account.uid
        val result = repository.requestPlayerAgentList(languageCode, uid, region, lToken, ltUid)
        result.fold(onSuccess = {
            return Result.success(it)
        }, onFailure = {
            return Result.failure(it)
        })
    }
}