/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import feature.agent.data.repository.AgentRepository
import feature.cover.data.repository.CoverImageRepository

class UpdateDatabaseUseCase(
    private val coverImageRepository: CoverImageRepository,
    private val agentRepository: AgentRepository
) {
    suspend fun updateCoverImages() {
        coverImageRepository.requestAndUpdateCoverImagesListDB()
    }

    suspend fun updateAgentsList() {
        agentRepository.requestAndUpdateAgentsListDB()
    }
}
