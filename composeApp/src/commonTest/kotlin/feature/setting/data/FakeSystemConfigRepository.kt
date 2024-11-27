/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.data

class FakeSystemConfigRepository : SystemConfigRepository {
    private var bannerIgnoreId = 1
    private var coverImageDBVersion = 1
    private var agentListDBVersion = 1
    private var wEngineListDBVersion = 1
    private var bangbooListDBVersion = 1
    private var driveListDBVersion = 1


    override fun getBannerIgnoreId(): Int {
        return bannerIgnoreId
    }

    override fun setBannerIgnoreId(value: Int) {
        bannerIgnoreId = value
    }

    override fun getCoverImageDBVersion(): Int {
        return coverImageDBVersion
    }

    override fun setCoverImageDBVersion(value: Int) {
        coverImageDBVersion = value
    }

    override fun getAgentListDBVersion(): Int {
        return agentListDBVersion
    }

    override fun setAgentListDBVersion(value: Int) {
        agentListDBVersion = value
    }

    override fun getWEngineListDBVersion(): Int {
        return wEngineListDBVersion
    }

    override fun setWEngineListDBVersion(value: Int) {
        wEngineListDBVersion = value
    }

    override fun getBangbooListDBVersion(): Int {
        return bangbooListDBVersion
    }

    override fun setBangbooListDBVersion(value: Int) {
        bangbooListDBVersion = value
    }

    override fun getDriveListDBVersion(): Int {
        return driveListDBVersion
    }

    override fun setDriveListDBVersion(value: Int) {
        driveListDBVersion = value
    }

    override fun clear() {
        bannerIgnoreId = 0
        coverImageDBVersion = 0
        agentListDBVersion = 0
        wEngineListDBVersion = 0
        bangbooListDBVersion = 0
        driveListDBVersion = 0
    }
}