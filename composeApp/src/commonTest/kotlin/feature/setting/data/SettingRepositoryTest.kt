/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

import com.russhwolf.settings.MapSettings
import utils.Language
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SettingRepositoryTest {
    private val fakeSettings = MapSettings()
    private val settingsRepository = PreferencesRepositoryImpl(fakeSettings)

    @Test
    fun `Get default color theme`() {
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        assertTrue(isDarkTheme)
    }

    @Test
    fun `Set color theme to light`() {
        settingsRepository.setIsDarkTheme(false)
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        assertFalse(isDarkTheme)
    }

    @Test
    fun `Get default language`() {
        val defaultLanguage = settingsRepository.getLanguageCode()
        assertEquals(defaultLanguage, "")
    }

    @Test
    fun `Set language to chinese`() {
        settingsRepository.setLanguage(Language.ChineseTraditional.code)
        val language = settingsRepository.getLanguageCode()
        assertEquals(language, Language.ChineseTraditional.code)
    }

    @Test
    fun `Delete all setting THAN Reset to default value`() {
        settingsRepository.clear()
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        val language = settingsRepository.getLanguageCode()
        assertTrue(isDarkTheme)
        assertEquals(language, "")
    }
}