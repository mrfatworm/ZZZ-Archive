/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WEnginesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setWEnginesList(agentsList: List<WEnginesListItemEntity>)

    @Query("SELECT * FROM WEnginesListItemEntity")
    fun getWEnginesList(): Flow<List<WEnginesListItemEntity>>

    @Query("DELETE FROM WEnginesListItemEntity")
    suspend fun deleteWEnginesList()
}