/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoverImageListItemEntity::class], version = 1
)
@ConstructedBy(CoverImagesListDBConstructor::class)
abstract class CoverImagesListDB : RoomDatabase() {
    abstract val coverImagesListDao: CoverImagesListDao

    companion object {
        const val DATABASE_NAME = "cover_images_list.db"
    }
}