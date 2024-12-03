package feature.splash

import androidx.lifecycle.ViewModel
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import feature.splash.model.SplashState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import utils.changePlatformLanguage

class SplashViewModel(
    private val themeUseCase: ThemeUseCase,
    private val uiScaleUseCase: UiScaleUseCase,
    private val languageUseCase: LanguageUseCase,
    private val appInfoUseCase: AppInfoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashState())
    val uiState = _uiState.asStateFlow()

    init {
        //settingsRepository.clear() // For test
        getPreferenceTheme()
        getPreferenceUiScale()
        initLanguage()
        getAppVersion()
    }

    private fun getPreferenceTheme() {
        val isDark = themeUseCase.getPreferenceIsDarkTheme()
        _uiState.update {
            it.copy(isDark = isDark)
        }
    }

    private fun getPreferenceUiScale() {
        val uiScale = uiScaleUseCase.getUiScale()
        val fontScale = uiScaleUseCase.getFontScale()
        _uiState.update {
            it.copy(uiScale = uiScale, fontScale = fontScale)
        }
    }

    private fun initLanguage() {
        val preferenceLangCode = languageUseCase.getLanguage().code
        if (preferenceLangCode != "") {
            changePlatformLanguage(preferenceLangCode)
        }
    }

    private fun getAppVersion() {
        val appInfo = appInfoUseCase.getAppVersion()
        _uiState.update {
            it.copy(appVersion = appInfo)
        }
    }
}