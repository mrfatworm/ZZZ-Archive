/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.FakeSettingRepository
import utils.Language
import kotlin.test.Test
import kotlin.test.assertEquals


class LanguageUseCaseTest {

    private val settingRepository = FakeSettingRepository()
    private val languageUseCase = LanguageUseCaseImpl(settingRepository)

    @Test
    fun `Get language`() {
        val result = languageUseCase.getLanguage()
        assertEquals(result, Language.English)
    }

    @Test
    fun `Set language`() {
        languageUseCase.setLanguage(Language.ChineseTraditional.code)
        val result = languageUseCase.getLanguage()
        assertEquals(result, Language.ChineseTraditional)
    }
}