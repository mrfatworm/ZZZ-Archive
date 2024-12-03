/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.data

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SettingsListener

/**
 * This class wraps all of the different operations that might be performed on a given [key], and adds an interface to
 * get and set it as a [String] value.
 */
sealed class SettingConfig<T>(
    private val settings: Settings, val key: String, private val defaultValue: T
) {
    protected abstract fun getStringValue(settings: Settings, key: String, defaultValue: T): String
    protected abstract fun setStringValue(settings: Settings, key: String, value: String)
    protected abstract fun addListener(
        settings: ObservableSettings, key: String, defaultValue: T, callback: (T) -> Unit
    ): SettingsListener

    private var listener: SettingsListener? = null

    fun remove() = settings.remove(key)
    fun exists(): Boolean = settings.hasKey(key)

    fun get(): String = getStringValue(settings, key, defaultValue)
    fun set(value: String): Boolean {
        return try {
            setStringValue(settings, key, value)
            true
        } catch (exception: Exception) {
            false
        }
    }

    var isLoggingEnabled: Boolean
        get() = listener != null
        set(value) {
            val settings = settings as? ObservableSettings ?: return
            listener = if (value) {
                listener?.deactivate() // just in case
                addListener(settings, key, defaultValue) { println("$key = ${get()}") }
            } else {
                listener?.deactivate()
                null
            }
        }

    override fun toString() = key
}

class StringSettingConfig(settings: Settings, key: String, defaultValue: String) :
    SettingConfig<String>(settings, key, defaultValue) {

    override fun getStringValue(settings: Settings, key: String, defaultValue: String): String =
        settings.getString(key, defaultValue)

    override fun setStringValue(settings: Settings, key: String, value: String) =
        settings.putString(key, value)

    override fun addListener(
        settings: ObservableSettings, key: String, defaultValue: String, callback: (String) -> Unit
    ): SettingsListener = settings.addStringListener(key, defaultValue, callback)
}

class IntSettingConfig(settings: Settings, key: String, defaultValue: Int) :
    SettingConfig<Int>(settings, key, defaultValue) {

    override fun getStringValue(settings: Settings, key: String, defaultValue: Int): String =
        settings.getInt(key, defaultValue).toString()

    override fun setStringValue(settings: Settings, key: String, value: String) =
        settings.putInt(key, value.toInt())

    override fun addListener(
        settings: ObservableSettings, key: String, defaultValue: Int, callback: (Int) -> Unit
    ): SettingsListener = settings.addIntListener(key, defaultValue, callback)
}

class FloatSettingConfig(settings: Settings, key: String, defaultValue: Float) :
    SettingConfig<Float>(settings, key, defaultValue) {

    override fun getStringValue(settings: Settings, key: String, defaultValue: Float): String =
        settings.getFloat(key, defaultValue).toString()

    override fun setStringValue(settings: Settings, key: String, value: String) =
        settings.putFloat(key, value.toFloat())

    override fun addListener(
        settings: ObservableSettings, key: String, defaultValue: Float, callback: (Float) -> Unit
    ): SettingsListener = settings.addFloatListener(key, defaultValue, callback)
}

class BooleanSettingConfig(settings: Settings, key: String, defaultValue: Boolean) :
    SettingConfig<Boolean>(settings, key, defaultValue) {

    override fun getStringValue(settings: Settings, key: String, defaultValue: Boolean): String =
        settings.getBoolean(key, defaultValue).toString()

    override fun setStringValue(settings: Settings, key: String, value: String) =
        settings.putBoolean(key, value.toBoolean())

    override fun addListener(
        settings: ObservableSettings,
        key: String,
        defaultValue: Boolean,
        callback: (Boolean) -> Unit
    ): SettingsListener = settings.addBooleanListener(key, defaultValue, callback)
}