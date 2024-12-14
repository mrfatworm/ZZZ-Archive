package root

import androidx.lifecycle.ViewModel
import feature.setting.domain.ThemeUseCase

class MainContainerViewModel(
    private val themeUseCase: ThemeUseCase
) : ViewModel() {
    private val _isDark = themeUseCase.getPreferenceIsDarkTheme()
    val isDark = _isDark

    suspend fun setIsDarkTheme(isDark: Boolean) {
        themeUseCase.setPreferenceIsDarkTheme(isDark)
    }
}