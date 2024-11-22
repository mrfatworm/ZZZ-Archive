package utils


enum class Language(val localName: String, val code: String, val officialNewsCode: String) {
    English("English", "en", "en-us"),
    ChineseTraditional("繁體中文", "zh", "zh-tw")
}

expect fun changePlatformLanguage(langCode: String)