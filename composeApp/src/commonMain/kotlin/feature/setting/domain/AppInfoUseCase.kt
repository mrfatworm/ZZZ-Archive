/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import com.mrfatworm.zzzarchive.ZzzConfig
import utils.getPlatform

interface AppInfoUseCase {
    fun getAppVersion(): String

    fun getDeviceInfo(): String

    fun getDeviceOs(): String
}

class AppInfoUseCaseImpl : AppInfoUseCase {
    override fun getAppVersion(): String = ZzzConfig.VERSION

    override fun getDeviceInfo(): String = getPlatform().deviceName

    override fun getDeviceOs(): String = getPlatform().operatingSystemName
}
