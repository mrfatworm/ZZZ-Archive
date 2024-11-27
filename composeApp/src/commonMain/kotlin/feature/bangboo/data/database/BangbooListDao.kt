/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BangbooListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setBangbooList(bangbooList: List<BangbooListItemEntity>)

    @Query("SELECT * FROM BangbooListItemEntity")
    fun getBangbooList(): Flow<List<BangbooListItemEntity>>

    @Query("DELETE FROM BangbooListItemEntity")
    suspend fun deleteBangbooList()
}