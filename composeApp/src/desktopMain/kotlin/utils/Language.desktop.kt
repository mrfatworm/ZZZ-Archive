/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package utils

import java.util.Locale

actual fun changeLanguage(langCode: String) {
    val locale = if (langCode == "") Locale.getDefault() else Locale(langCode)
    Locale.setDefault(locale)
}