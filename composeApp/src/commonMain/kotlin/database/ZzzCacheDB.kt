/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDao
import feature.agent.data.database.AgentsListItemEntity
import feature.cover.data.database.CoverImageListItemEntity
import feature.cover.data.database.CoverImagesListDao

/**
 * Everything here is a cache of the ZZZ-Archive-Asset repo and can be re-downloaded, which is why
 * the database is built with `fallbackToDestructiveMigration(true)` and why a schema change never
 * needs a hand-written migration. Only add an entity here if losing its rows is harmless.
 */
@Database(
    entities = [AgentsListItemEntity::class, CoverImageListItemEntity::class],
    version = 1
)
@ConstructedBy(ZzzCacheDBConstructor::class)
abstract class ZzzCacheDB : RoomDatabase() {
    abstract val agentsListDao: AgentsListDao

    abstract val coverImagesListDao: CoverImagesListDao

    companion object {
        const val DATABASE_NAME = "zzz_cache.db"

        /**
         * The separate databases this one replaced. Their rows are re-downloadable, so installs
         * that predate the merge just drop the files instead of migrating.
         */
        val LEGACY_DATABASE_NAMES = listOf("agent_list.db", "cover_images_list.db")
    }
}
