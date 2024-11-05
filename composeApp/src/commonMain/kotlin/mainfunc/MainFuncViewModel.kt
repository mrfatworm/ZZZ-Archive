package mainfunc

import androidx.lifecycle.ViewModel
import app.setting.data.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainFuncViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()

    init {
        _isDark.value = settingsRepository.getIsDarkTheme()
    }

    fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        settingsRepository.setIsDarkTheme(isDark)
    }
}