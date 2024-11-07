/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.data

interface AppInfoRepository {
    fun getAppVersion(): String
    fun getDeviceInfo(): String
    fun getDeviceOs(): String
}