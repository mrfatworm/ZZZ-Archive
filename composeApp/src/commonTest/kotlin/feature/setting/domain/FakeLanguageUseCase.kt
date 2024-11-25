/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

import utils.Language

/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */


class FakeLanguageUseCase : LanguageUseCase {
    private var language = Language.English

    override fun getLanguage(): Language {
        return language
    }

    override fun setLanguage(langCode: String) {
        language = Language.entries.firstOrNull { it.code == langCode } ?: Language.English
    }
}