package app.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import setting.SettingsRepository

class SplashViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {
    private val _isDark = MutableStateFlow(true)
    val isDark: StateFlow<Boolean> = _isDark

    init {
        _isDark.value = settingsRepository.getIsDarkTheme()
    }

}