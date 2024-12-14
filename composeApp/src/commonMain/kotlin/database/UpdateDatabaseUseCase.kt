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
import feature.setting.domain.LanguageUseCase
import feature.wengine.data.repository.WEngineRepository
import kotlinx.coroutines.flow.first

class UpdateDatabaseUseCase(
    private val assetVersionRepository: AssetVersionRepository,
    private val coverImageRepository: CoverImageRepository,
    private val agentRepository: AgentRepository,
    private val wEngineRepository: WEngineRepository,
    private val bangbooRepository: BangbooRepository,
    private val driveRepository: DriveRepository,
    private val systemConfigRepository: SystemConfigRepository,
    private val languageUseCase: LanguageUseCase
) {
    suspend fun updateAssetsIfNewVersionAvailable() {
        val language = languageUseCase.getLanguage().first().officialCode
        assetVersionRepository.requestAssetVersion().onSuccess { assetVersionResponse ->
            if (assetVersionResponse.coverImagesList > systemConfigRepository.getCoverImageDBVersion()
                    .first()
            ) {
                coverImageRepository.requestAndUpdateCoverImagesListDB().onSuccess {
                    systemConfigRepository.setCoverImageDBVersion(assetVersionResponse.coverImagesList)
                }
            }
            if (assetVersionResponse.agentsList > systemConfigRepository.getAgentListDBVersion()
                    .first()
            ) {
                agentRepository.requestAndUpdateAgentsListDB(language).onSuccess {
                    systemConfigRepository.setAgentListDBVersion(assetVersionResponse.agentsList)
                }
            }
            if (assetVersionResponse.wEnginesList > systemConfigRepository.getWEngineListDBVersion()
                    .first()
            ) {
                wEngineRepository.requestAndUpdateWEnginesListDB(language).onSuccess {
                    systemConfigRepository.setWEngineListDBVersion(assetVersionResponse.wEnginesList)
                }
            }
            if (assetVersionResponse.bangbooList > systemConfigRepository.getBangbooListDBVersion()
                    .first()
            ) {
                bangbooRepository.requestAndUpdateBangbooListDB(language).onSuccess {
                    systemConfigRepository.setBangbooListDBVersion(assetVersionResponse.bangbooList)
                }
            }
            if (assetVersionResponse.drivesList > systemConfigRepository.getDriveListDBVersion()
                    .first()
            ) {
                driveRepository.requestAndUpdateDrivesListDB(language).onSuccess {
                    systemConfigRepository.setDriveListDBVersion(assetVersionResponse.drivesList)
                }
            }
        }
    }

    suspend fun resetWikiDatabaseVersion() {
        systemConfigRepository.setAgentListDBVersion(0)
        systemConfigRepository.setWEngineListDBVersion(0)
        systemConfigRepository.setBangbooListDBVersion(0)
        systemConfigRepository.setDriveListDBVersion(0)
    }
}