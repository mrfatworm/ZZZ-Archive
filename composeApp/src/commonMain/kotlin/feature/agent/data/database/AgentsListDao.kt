/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentsListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAgentsList(agentsList: List<AgentsListItemEntity>)

    @Query("SELECT * FROM AgentsListItemEntity")
    fun getAgentsList(): Flow<List<AgentsListItemEntity>>

    @Query("DELETE FROM AgentsListItemEntity")
    suspend fun deleteAgentsList()
}