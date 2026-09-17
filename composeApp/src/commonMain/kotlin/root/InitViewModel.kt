package root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.hoyolab.domain.HoYoLabCredentialMigrationUseCase
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.changePlatformLanguage

class InitViewModel(
    private val themeUseCase: ThemeUseCase,
    private val uiScaleUseCase: UiScaleUseCase,
    private val languageUseCase: LanguageUseCase,
    private val appInfoUseCase: AppInfoUseCase,
    private val hoYoLabCredentialMigrationUseCase: HoYoLabCredentialMigrationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(InitState())
    val uiState = _uiState.asStateFlow()

    init {
        // settingsRepository.clear() // For test
        viewModelScope.launch {
            // Ahead of everything else, and not in a child coroutine: `isLoading` drops as soon as
            // the theme arrives, and no screen may read a credential before it has been moved.
            migrateHoYoLabCredentials()
            launch { observeIsDarkTheme() }
            launch { initUiScale() }
            launch { initLanguage() }
            launch { getAppVersion() }
        }
    }

    private suspend fun observeIsDarkTheme() {
        themeUseCase.getPreferenceIsDarkTheme().collect { isDark ->
            _uiState.update {
                it.copy(isDark = isDark, isLoading = false)
            }
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

    /**
     * One-shot move of the HoYoLab cookies into the platform key store. A key store that refuses
     * to answer must not hold the splash screen: the migrated flag then stays unset and the move
     * is retried on the next launch.
     */
    private suspend fun migrateHoYoLabCredentials() {
        val result = runCatching { hoYoLabCredentialMigrationUseCase.migrateIfNeeded() }.getOrNull() ?: return
        _uiState.update {
            it.copy(unlinkedHoYoLabAccounts = result.droppedAccounts)
        }
    }

    private fun getAppVersion() {
        val appInfo = appInfoUseCase.getAppVersion()
        _uiState.update {
            it.copy(appVersion = appInfo)
        }
    }
}
