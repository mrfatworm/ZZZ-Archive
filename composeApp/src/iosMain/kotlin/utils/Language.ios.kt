/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import platform.Foundation.NSUserDefaults

actual fun changePlatformLanguage(langCode: String) {
    if (langCode == "") {
        NSUserDefaults.standardUserDefaults.setObject(null, "AppleLanguages")
    } else {
        NSUserDefaults.standardUserDefaults.setObject(arrayListOf(langCode), "AppleLanguages")
    }
}