package root

import androidx.lifecycle.ViewModel
import feature.setting.domain.ThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainContainerViewModel(
    private val themeUseCase: ThemeUseCase
) : ViewModel() {
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()

    init {
        _isDark.value = themeUseCase.getIsDarkTheme()
    }

    fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        themeUseCase.setIsDarkTheme(isDark)
    }
}