/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWEnginesListDao : WEnginesListDao {
    private val wEnginesList = mutableListOf(stubWEnginesListItemEntity)

    override suspend fun setWEnginesList(agentsList: List<WEnginesListItemEntity>) {
        wEnginesList.clear()
        wEnginesList.addAll(agentsList)
    }

    override fun getWEnginesList(): Flow<List<WEnginesListItemEntity>> = flow {
        emit(wEnginesList)
    }

    override suspend fun deleteWEnginesList() {
        wEnginesList.clear()
    }
}