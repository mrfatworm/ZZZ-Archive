/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

interface SystemConfigRepository {
    fun getBannerIgnoreId(): Int
    fun setBannerIgnoreId(value: Int)
    fun getCoverImageDBVersion(): Int
    fun setCoverImageDBVersion(value: Int)
    fun getAgentListDBVersion(): Int
    fun setAgentListDBVersion(value: Int)
    fun getWEngineListDBVersion(): Int
    fun setWEngineListDBVersion(value: Int)
    fun getBangbooListDBVersion(): Int
    fun setBangbooListDBVersion(value: Int)
    fun getDriveListDBVersion(): Int
    fun setDriveListDBVersion(value: Int)
    fun clear()
}