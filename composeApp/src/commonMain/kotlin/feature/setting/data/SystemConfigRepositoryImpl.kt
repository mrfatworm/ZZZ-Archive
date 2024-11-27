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
class SystemConfigRepositoryImpl(private val settings: Settings) : SystemConfigRepository {
    private val bannerIgnoreId: SettingConfig<Int> =
        IntSettingConfig(settings, "BANNER_IGNORE_ID", 0)
    private val coverImageDBVersion: SettingConfig<Int> =
        IntSettingConfig(settings, "COVER_IMAGE_DB_VERSION", 0)
    private val agentsListDBVersion: SettingConfig<Int> =
        IntSettingConfig(settings, "AGENTS_LIST_DB_VERSION", 0)
    private val wEngineListDBVersion: SettingConfig<Int> =
        IntSettingConfig(settings, "W_ENGINE_LIST_DB_VERSION", 0)
    private val bangbooListDBVersion: SettingConfig<Int> =
        IntSettingConfig(settings, "BANGBOO_LIST_DB_VERSION", 0)
    private val driveListDBVersion: SettingConfig<Int> =
        IntSettingConfig(settings, "DRIVE_LIST_DB_VERSION", 0)


    override fun getBannerIgnoreId(): Int {
        return bannerIgnoreId.get().toInt()
    }

    override fun setBannerIgnoreId(value: Int) {
        bannerIgnoreId.set(value.toString())
    }

    override fun getCoverImageDBVersion(): Int {
        return coverImageDBVersion.get().toInt()
    }

    override fun setCoverImageDBVersion(value: Int) {
        coverImageDBVersion.set(value.toString())
    }

    override fun getAgentListDBVersion(): Int {
        return agentsListDBVersion.get().toInt()
    }

    override fun setAgentListDBVersion(value: Int) {
        agentsListDBVersion.set(value.toString())
    }

    override fun getWEngineListDBVersion(): Int {
        return wEngineListDBVersion.get().toInt()
    }

    override fun setWEngineListDBVersion(value: Int) {
        wEngineListDBVersion.set(value.toString())
    }

    override fun getBangbooListDBVersion(): Int {
        return bangbooListDBVersion.get().toInt()
    }

    override fun setBangbooListDBVersion(value: Int) {
        bangbooListDBVersion.set(value.toString())
    }

    override fun getDriveListDBVersion(): Int {
        return driveListDBVersion.get().toInt()
    }

    override fun setDriveListDBVersion(value: Int) {
        driveListDBVersion.set(value.toString())
    }

    override fun clear() = settings.clear()
}

