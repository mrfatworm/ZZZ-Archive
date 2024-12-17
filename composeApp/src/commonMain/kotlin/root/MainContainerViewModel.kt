package root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.setting.domain.ThemeUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainContainerViewModel(private val themeUseCase: ThemeUseCase) : ViewModel() {

    private var isDarkThemeJob: Job? = null

    private val _isDark = MutableStateFlow(true)
    val isDark = _isDark.onStart {
        observeIsDarkTheme()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _isDark.value)


    private fun observeIsDarkTheme() {
        isDarkThemeJob?.cancel()
        isDarkThemeJob = themeUseCase.getPreferenceIsDarkTheme().onEach { isDark ->
            _isDark.update { isDark }
        }.launchIn(viewModelScope)
    }

    suspend fun setIsDarkTheme(isDark: Boolean) {
        themeUseCase.setPreferenceIsDarkTheme(isDark)
    }
}