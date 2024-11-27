/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import feature.agent.data.repository.AgentRepository
import feature.bangboo.data.repository.BangbooRepository
import feature.cover_image.data.repository.CoverImageRepository
import feature.drive.data.respository.DriveRepository
import feature.home.data.AssetVersionRepository
import feature.setting.data.SystemConfigRepository
import feature.wengine.data.repository.WEngineRepository

class UpdateDatabaseUseCase(
    private val assetVersionRepository: AssetVersionRepository,
    private val coverImageRepository: CoverImageRepository,
    private val agentRepository: AgentRepository,
    private val wEngineRepository: WEngineRepository,
    private val bangbooRepository: BangbooRepository,
    private val driveRepository: DriveRepository,
    private val systemConfigRepository: SystemConfigRepository,
) {
    suspend fun updateAssetsIfNewVersionAvailable() {
        assetVersionRepository.requestAssetVersion().onSuccess { assetVersionResponse ->
            if (assetVersionResponse.coverImagesList > systemConfigRepository.getCoverImageDBVersion()) {
                coverImageRepository.requestAndUpdateCoverImagesListDB().onSuccess {
                    systemConfigRepository.setCoverImageDBVersion(assetVersionResponse.coverImagesList)
                }
            }
            if (assetVersionResponse.agentsList > systemConfigRepository.getAgentListDBVersion()) {
                agentRepository.requestAndUpdateAgentsListDB().onSuccess {
                    systemConfigRepository.setAgentListDBVersion(assetVersionResponse.agentsList)
                }
            }
            if (assetVersionResponse.wEnginesList > systemConfigRepository.getWEngineListDBVersion()) {
                println("Lance Current Version: ${systemConfigRepository.getWEngineListDBVersion()}")
                println("Lance Network Version: ${assetVersionResponse.wEnginesList}")
                wEngineRepository.requestAndUpdateWEnginesListDB().onSuccess {
                    systemConfigRepository.setWEngineListDBVersion(assetVersionResponse.wEnginesList)
                }
            }
            if (assetVersionResponse.bangbooList > systemConfigRepository.getBangbooListDBVersion()) {
                bangbooRepository.requestAndUpdateBangbooListDB().onSuccess {
                    systemConfigRepository.setBangbooListDBVersion(assetVersionResponse.bangbooList)
                }
            }
            if (assetVersionResponse.drivesList > systemConfigRepository.getDriveListDBVersion()) {
                driveRepository.requestAndUpdateDrivesListDB().onSuccess {
                    systemConfigRepository.setDriveListDBVersion(assetVersionResponse.drivesList)
                }
            }
        }
    }

    fun resetWikiDatabaseVersion() {
        systemConfigRepository.setAgentListDBVersion(0)
        systemConfigRepository.setWEngineListDBVersion(0)
        systemConfigRepository.setBangbooListDBVersion(0)
        systemConfigRepository.setDriveListDBVersion(0)
    }
}