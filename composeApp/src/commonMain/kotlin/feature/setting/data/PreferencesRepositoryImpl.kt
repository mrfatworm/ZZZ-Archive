/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data


import com.russhwolf.settings.Settings

/**
 * This class demonstrates common code exercising all of the functionality of the [Settings] class.
 * The majority of this functionality is delegated to [SettingConfig] subclasses for each supported type.
 */
class PreferencesRepositoryImpl(private val settings: Settings) : PreferencesRepository {

    private val isDarkTheme: SettingConfig<Boolean> =
        BooleanSettingConfig(settings, "IS_DARK_THEME", true)
    private val language: SettingConfig<String> = StringSettingConfig(settings, "LANGUAGE", "")
    private val uiScale: SettingConfig<Float> = FloatSettingConfig(settings, "UI_SCALE", 1f)
    private val fontScale: SettingConfig<Float> = FloatSettingConfig(settings, "FONT_SCALE", 1f)

    override fun getIsDarkTheme(): Boolean {
        return isDarkTheme.get().toBoolean()
    }

    override fun setIsDarkTheme(value: Boolean) {
        isDarkTheme.set(value.toString())
    }

    override fun getLanguageCode(): String {
        return language.get()
    }

    override fun setLanguage(langCode: String) {
        language.set(langCode)
    }

    override fun getUiScale(): Float {
        return uiScale.get().toFloat()
    }

    override fun setUiScale(value: Float) {
        uiScale.set(value.toString())
    }

    override fun getFontScale(): Float {
        return fontScale.get().toFloat()
    }

    override fun setFontScale(value: Float) {
        fontScale.set(value.toString())
    }

    override fun clear() = settings.clear()
}

