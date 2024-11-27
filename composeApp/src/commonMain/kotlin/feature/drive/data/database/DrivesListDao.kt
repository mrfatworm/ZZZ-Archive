/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DrivesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setDrivesList(agentsList: List<DrivesListItemEntity>)

    @Query("SELECT * FROM DrivesListItemEntity")
    fun getDrivesList(): Flow<List<DrivesListItemEntity>>

    @Query("DELETE FROM DrivesListItemEntity")
    suspend fun deleteDrivesList()
}