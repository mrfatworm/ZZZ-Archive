package utils


enum class Language(val localName: String, val project: String, val assetLang: String) {
    English("English", "en", "en-us"),
    ChineseTraditional("繁體中文", "zh", "zh-tw")
}

expect fun changeLanguage(langCode: String)