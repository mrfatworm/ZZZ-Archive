/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.FakePreferenceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import utils.Language
import kotlin.test.Test
import kotlin.test.assertEquals


class LanguageUseCaseTest {

    private val settingRepository = FakePreferenceRepository()
    private val languageUseCase = LanguageUseCaseImpl(settingRepository)

    @Test
    fun `Get language`() = runTest {
        val result = languageUseCase.getLanguage().first()
        assertEquals(Language.English, result)
    }

    @Test
    fun `Set language`() = runTest {
        languageUseCase.setLanguage(Language.ChineseTraditional.code)
        val result = languageUseCase.getLanguage().first()
        assertEquals(Language.ChineseTraditional, result)
    }
}