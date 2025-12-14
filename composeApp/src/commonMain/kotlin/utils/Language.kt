package utils

enum class Language(val localName: String, val code: String, val officialCode: String) {
    English(localName = "English", code = "en", officialCode = "en-us"),
    ChineseTraditional(localName = "繁體中文", code = "zh", officialCode = "zh-tw"),
    Japanese(localName = "日本語", code = "ja", officialCode = "ja-jp")
}

expect fun changePlatformLanguage(langCode: String)
