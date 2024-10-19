package utils

enum class Language(var localName: String, var code: String, var folderSuffix: String) {
    Auto("Auto", "", ""),
    English("English", "en", "en"),
    ChineseTraditional("繁體中文", "zh-TW", "zh")
}