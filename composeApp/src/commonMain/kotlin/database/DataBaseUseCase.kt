/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import feature.agent.data.database.AgentsListDao

class DataBaseUseCase(private val agentsListDao: AgentsListDao) {

    suspend fun deleteAllDatabase() {
        agentsListDao.deleteAgentsList()
    }
}