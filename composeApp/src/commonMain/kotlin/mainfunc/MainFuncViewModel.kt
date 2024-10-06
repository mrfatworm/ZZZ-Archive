package mainfunc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mainfunc.data.BannerRepository
import mainfunc.model.BannerResponse
import setting.SettingsRepository
import utils.ZzzResult

class MainFuncViewModel(
    private val bannerRepository: BannerRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()
    private val _banner = MutableStateFlow<BannerResponse?>(null)
    val banner = _banner.asStateFlow()
    private var ignoreId = 0

    init {
        _isDark.value = settingsRepository.getIsDarkTheme()
        ignoreId = settingsRepository.getBannerIgnoreId()
        viewModelScope.launch {
            fetchBanner()
        }
    }

    private suspend fun fetchBanner() {
        when (val result = bannerRepository.getBanner()) {
            is ZzzResult.Success -> {
                if (result.data.id > ignoreId) {
                    _banner.value = result.data
                }
            }

            is ZzzResult.Error -> {
                println("get banner error: ${result.exception}")
            }
        }
    }

    fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        settingsRepository.setIsDarkTheme(isDark)
    }

    fun setBannerIgnoreId(id: Int) {
        ignoreId = id
        settingsRepository.setBannerIgnoreId(id)
        _banner.value = null
    }

}