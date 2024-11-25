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
    private val settingsRepository = SettingsRepositoryImpl(fakeSettings)

    @Test
    fun `Get Default Color Theme`() {
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        assertTrue(isDarkTheme)
    }

    @Test
    fun `Set Color Theme to Light`() {
        settingsRepository.setIsDarkTheme(false)
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        assertFalse(isDarkTheme)
    }

    @Test
    fun `Get Default Language`() {
        val defaultLanguage = settingsRepository.getLanguageCode()
        assertEquals(defaultLanguage, "")
    }

    @Test
    fun `Set Language to Chinese`() {
        settingsRepository.setLanguage(Language.ChineseTraditional.code)
        val language = settingsRepository.getLanguageCode()
        assertEquals(language, Language.ChineseTraditional.code)
    }

    @Test
    fun `Get Default Banner Ignore Id`() {
        val ignoreId = settingsRepository.getBannerIgnoreId()
        assertEquals(ignoreId, 0)
    }

    @Test
    fun `Set Banner Ignore Id`() {
        settingsRepository.setBannerIgnoreId(1)
        val ignoreId = settingsRepository.getBannerIgnoreId()
        assertEquals(ignoreId, 1)
    }

    @Test
    fun `Delete All Setting and Get Default Value`() {
        settingsRepository.clear()
        val isDarkTheme = settingsRepository.getIsDarkTheme()
        val language = settingsRepository.getLanguageCode()
        assertTrue(isDarkTheme)
        assertEquals(language, "")
    }
}