/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.data

import com.mrfatworm.zzzarchive.ZzzConfig
import utils.getPlatform

class AppInfoRepositoryImpl : AppInfoRepository {
    override fun getAppVersion(): String {
        return ZzzConfig.VERSION
    }

    override fun getDeviceInfo(): String {
        return getPlatform().deviceName
    }

    override fun getDeviceOs(): String {
        return getPlatform().operatingSystemName
    }
}