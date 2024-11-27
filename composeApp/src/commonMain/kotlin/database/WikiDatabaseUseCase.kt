/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import feature.agent.data.database.AgentsListDao
import feature.bangboo.data.database.BangbooListDao
import feature.drive.data.database.DrivesListDao
import feature.wengine.data.database.WEnginesListDao

class WikiDatabaseUseCase(
    private val agentsListDao: AgentsListDao,
    private val wEnginesListDao: WEnginesListDao,
    private val bangbooListDao: BangbooListDao,
    private val drivesListDao: DrivesListDao
) {
    suspend fun deleteAllDatabase() {
        agentsListDao.deleteAgentsList()
        wEnginesListDao.deleteWEnginesList()
        bangbooListDao.deleteBangbooList()
        drivesListDao.deleteDrivesList()
    }
}