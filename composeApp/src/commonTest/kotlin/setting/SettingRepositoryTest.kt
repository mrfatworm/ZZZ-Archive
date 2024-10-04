package setting

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.russhwolf.settings.MapSettings
import utils.Language
import kotlin.test.Test

class SettingRepositoryTest {
    private val fakeSettings = MapSettings()
    private val settingsRepository = SettingsRepositoryImpl(fakeSettings)

    @Test
    fun `Get Default Color Theme`() {
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        assertThat(isDarkTheme).isEqualTo(true)
    }

    @Test
    fun `Set Color Theme to Light`() {
        settingsRepository.setIsDarkTheme(false)
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        assertThat(isDarkTheme).isEqualTo(false)
    }

    @Test
    fun `Get Default Language`() {
        val defaultLanguage = settingsRepository.getLanguage()
        assertThat(defaultLanguage).isEqualTo(Language.En)
    }

    @Test
    fun `Set Language to Chinese`() {
        settingsRepository.setLanguage(Language.Zh)
        val language = settingsRepository.getLanguage()
        assertThat(language).isEqualTo(Language.Zh)
    }

    @Test
    fun `Delete All Setting and Get Default Value`() {
        settingsRepository.clear()
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        val language = settingsRepository.getLanguage()
        assertThat(isDarkTheme).isEqualTo(true)
        assertThat(language).isEqualTo(Language.En)
    }
}