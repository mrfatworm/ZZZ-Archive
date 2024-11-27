/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoverImagesListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCoverImagesList(coverImagesList: List<CoverImageListItemEntity>)

    @Query("SELECT * FROM CoverImageListItemEntity")
    fun getCoverImagesList(): Flow<List<CoverImageListItemEntity>>

    @Query("DELETE FROM CoverImageListItemEntity")
    suspend fun deleteCoverImagesList()
}