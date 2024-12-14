package feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import feature.splash.model.SplashState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            launch { initIsDarkTheme() }
            launch { initUiScale() }
            launch { initLanguage() }
            launch { getAppVersion() }
        }
    }

    private suspend fun initIsDarkTheme() {
        val isDark = themeUseCase.getPreferenceIsDarkTheme().first()
        _uiState.update {
            it.copy(isDark = isDark)
        }
    }

    private suspend fun initUiScale() {
        val uiScale = uiScaleUseCase.getUiScale().first()
        val fontScale = uiScaleUseCase.getFontScale().first()
        _uiState.update {
            it.copy(uiScale = uiScale, fontScale = fontScale)
        }
    }

    private suspend fun initLanguage() {
        val preferenceLangCode = languageUseCase.getLanguage().first().code
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