/* Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.Room
import androidx.room.RoomDatabase
import feature.hoyolab.data.database.HoYoLabAccountDB
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class RoomDatabaseFactory {
    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory =
            NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
        return requireNotNull(documentDirectory?.path)
    }

    private fun databasePath(databaseName: String): String = documentDirectory() + "/$databaseName"

    actual fun createCacheDatabase(): RoomDatabase.Builder<ZzzCacheDB> = Room.databaseBuilder<ZzzCacheDB>(
        name = databasePath(ZzzCacheDB.DATABASE_NAME)
    )

    actual fun createHoYoLabAccountDatabase(): RoomDatabase.Builder<HoYoLabAccountDB> =
        Room.databaseBuilder<HoYoLabAccountDB>(
            name = databasePath(HoYoLabAccountDB.DATABASE_NAME)
        )

    @OptIn(ExperimentalForeignApi::class)
    actual fun deleteLegacyCacheDatabases() {
        val fileManager = NSFileManager.defaultManager
        ZzzCacheDB.LEGACY_DATABASE_NAMES.forEach { databaseName ->
            val path = databasePath(databaseName)
            listOf(path, "$path-wal", "$path-shm", "$path.lck").forEach {
                fileManager.removeItemAtPath(it, error = null)
            }
        }
    }
}
