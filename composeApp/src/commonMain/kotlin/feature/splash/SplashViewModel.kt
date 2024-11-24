package feature.splash

import androidx.lifecycle.ViewModel
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import utils.changePlatformLanguage

class SplashViewModel(
    themeUseCase: ThemeUseCase,
    languageUseCase: LanguageUseCase,
    private val appInfoUseCase: AppInfoUseCase
) : ViewModel() {
    private val _isDark = MutableStateFlow(true)
    val isDark: StateFlow<Boolean> = _isDark
    private val _appVersion = MutableStateFlow("")
    val appVersion: StateFlow<String> = _appVersion

    init {
        //settingsRepository.clear() // For test
        _isDark.value = themeUseCase.getIsDarkTheme()
        initLanguage(languageUseCase.getLanguage().code)
        getAppVersion()
    }


    private fun initLanguage(langCode: String) {
        if (langCode != "") {
            changePlatformLanguage(langCode)
        }
    }

    private fun getAppVersion() {
        _appVersion.value = appInfoUseCase.getAppVersion()
    }
}