/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

/**
 * The values deliberately differ from `settingState`'s defaults, so a test asserting on them proves
 * the ViewModel actually read the use case instead of leaving the initial state untouched.
 */
class FakeAppInfoUseCase : AppInfoUseCase {
    override fun getAppVersion(): String = "Fake 2077.12.31"

    override fun getDeviceInfo(): String = "Fake Device"

    override fun getDeviceOs(): String = "Fake OS 1"
}
