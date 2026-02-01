/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import feature.agent.data.repository.AgentRepository
import feature.cover.data.repository.CoverImageRepository
import feature.home.data.AssetVersionRepository
import feature.setting.data.SystemConfigRepository
import kotlinx.coroutines.flow.first

class UpdateDatabaseUseCase(
    private val assetVersionRepository: AssetVersionRepository,
    private val coverImageRepository: CoverImageRepository,
    private val agentRepository: AgentRepository,
    private val systemConfigRepository: SystemConfigRepository
) {
    suspend fun updateAssetsIfNewVersionAvailable() {
        assetVersionRepository.requestAssetVersion().onSuccess { assetVersionResponse ->
            if (assetVersionResponse.coverImagesList >
                systemConfigRepository
                    .getCoverImageDBVersion()
                    .first()
            ) {
                coverImageRepository.requestAndUpdateCoverImagesListDB().onSuccess {
                    systemConfigRepository.setCoverImageDBVersion(assetVersionResponse.coverImagesList)
                }
            }
            if (assetVersionResponse.agentsList >
                systemConfigRepository
                    .getAgentListDBVersion()
                    .first()
            ) {
                agentRepository.requestAndUpdateAgentsListDB().onSuccess {
                    systemConfigRepository.setAgentListDBVersion(assetVersionResponse.agentsList)
                }
            }
        }
    }

    suspend fun resetWikiDatabaseVersion() {
        systemConfigRepository.setAgentListDBVersion(0)
    }
}
