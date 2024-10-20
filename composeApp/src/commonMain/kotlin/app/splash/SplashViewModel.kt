package app.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import setting.SettingsRepository
import utils.changeLanguage

class SplashViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {
    private val _isDark = MutableStateFlow(true)
    val isDark: StateFlow<Boolean> = _isDark

    init {
        //settingsRepository.clear() // For test
        _isDark.value = settingsRepository.getIsDarkTheme()
        initLanguage(settingsRepository.getLanguage())
    }


    private fun initLanguage(langCode: String) {
        if (langCode != "") {
            changeLanguage(langCode)
        }
    }
}