/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Language

/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */


class FakeLanguageUseCase : LanguageUseCase {
    private var language = Language.English

    override fun getLanguage(): Flow<Language> = flow {
        emit(language)
    }

    override suspend fun setLanguage(langCode: String) {
        language = Language.entries.firstOrNull { it.code == langCode } ?: Language.English
    }
}