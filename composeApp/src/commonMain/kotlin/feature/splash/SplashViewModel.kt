package feature.splash

import androidx.lifecycle.ViewModel
import feature.setting.data.AppInfoRepository
import feature.setting.data.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import utils.changePlatformLanguage

class SplashViewModel(
    private val settingsRepository: SettingsRepository,
    private val appInfoRepository: AppInfoRepository
) : ViewModel() {
    private val _isDark = MutableStateFlow(true)
    val isDark: StateFlow<Boolean> = _isDark
    private val _appVersion = MutableStateFlow("")
    val appVersion: StateFlow<String> = _appVersion

    init {
        //settingsRepository.clear() // For test
        _isDark.value = settingsRepository.getIsDarkTheme()
        initLanguage(settingsRepository.getLanguageCode())
        getAppVersion()
    }


    private fun initLanguage(langCode: String) {
        if (langCode != "") {
            changePlatformLanguage(langCode)
        }
    }

    private fun getAppVersion() {
        _appVersion.value = appInfoRepository.getAppVersion()
    }
}