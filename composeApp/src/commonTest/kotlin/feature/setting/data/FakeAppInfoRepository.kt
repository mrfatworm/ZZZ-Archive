/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

class FakeAppInfoRepository : AppInfoRepository {
    override fun getAppVersion(): String {
        return "Lucy 2024.11"
    }

    override fun getDeviceInfo(): String {
        return "Pixel 9 Pro"
    }

    override fun getDeviceOs(): String {
        return "Android 35"
    }

}