/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

//import utils.Language
//import kotlin.test.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertFalse
//import kotlin.test.assertTrue
//
//
// // Multiplatform Setting Test, deprecated after migrate to DataStore
//class SettingRepositoryTest {
//    private val fakeSettings = MapSettings()
//    private val settingsRepository = PreferencesRepositoryImpl(fakeSettings)
//
//    @Test
//    fun `Get default color theme`() {
//        val isDarkTheme = settingsRepository.getIsDarkTheme()
//        assertTrue(isDarkTheme)
//    }
//
//    @Test
//    fun `Set color theme to light`() {
//        settingsRepository.setIsDarkTheme(false)
//        val isDarkTheme = settingsRepository.getIsDarkTheme()
//        assertFalse(isDarkTheme)
//    }
//
//    @Test
//    fun `Get default language`() {
//        val defaultLanguage = settingsRepository.getLanguageCode()
//        assertEquals("", defaultLanguage)
//    }
//
//    @Test
//    fun `Set language to chinese`() {
//        settingsRepository.setLanguage(Language.ChineseTraditional.code)
//        val language = settingsRepository.getLanguageCode()
//        assertEquals(Language.ChineseTraditional.code, language)
//    }
//
//    @Test
//    fun `Delete all setting THEN Reset to default value`() {
//        settingsRepository.clear()
//        val isDarkTheme = settingsRepository.getIsDarkTheme()
//        val language = settingsRepository.getLanguageCode()
//        assertTrue(isDarkTheme)
//        assertEquals("", language)
//    }
//}