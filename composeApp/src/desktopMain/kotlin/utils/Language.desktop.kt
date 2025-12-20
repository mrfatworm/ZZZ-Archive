/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import java.util.Locale

actual fun changePlatformLanguage(langCode: String) {
    val locale = if (langCode == "") {
        Locale.getDefault()
    } else {
        val parts = langCode.split("-", limit = 2)
        if (parts.size == 2) {
            Locale(parts[0], parts[1])
        } else {
            Locale(langCode)
        }
    }
    Locale.setDefault(locale)
}
