/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package utils

import platform.Foundation.NSUserDefaults

actual fun changeLanguage(langCode: String) {
    if (langCode == "") {
        NSUserDefaults.standardUserDefaults.setObject(null, "AppleLanguages")
    } else {
        NSUserDefaults.standardUserDefaults.setObject(arrayListOf(langCode), "AppleLanguages")
    }
}