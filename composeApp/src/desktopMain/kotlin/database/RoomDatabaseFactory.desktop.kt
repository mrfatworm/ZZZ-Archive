/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.Room
import androidx.room.RoomDatabase
import feature.hoyolab.data.database.HoYoLabAccountDB
import java.io.File

actual class RoomDatabaseFactory {
    // Windows keeps every database in one shared directory; elsewhere each one gets a directory of
    // its own, named after the database.
    private fun appDataDir(databaseName: String): File {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> File(System.getenv("APPDATA"), "ZZZ Archive")
            else -> File(System.getProperty("java.io.tmpdir"), databaseName)
        }
    }

    private inline fun <reified T : RoomDatabase> createDB(databaseName: String): RoomDatabase.Builder<T> {
        val appDataDir = appDataDir(databaseName)
        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }
        return Room.databaseBuilder(File(appDataDir, databaseName).absolutePath)
    }

    actual fun createCacheDatabase(): RoomDatabase.Builder<ZzzCacheDB> = createDB(ZzzCacheDB.DATABASE_NAME)

    actual fun createHoYoLabAccountDatabase(): RoomDatabase.Builder<HoYoLabAccountDB> =
        createDB(HoYoLabAccountDB.DATABASE_NAME)

    actual fun deleteLegacyCacheDatabases() {
        ZzzCacheDB.LEGACY_DATABASE_NAMES.forEach { databaseName ->
            val appDataDir = appDataDir(databaseName)
            // SQLite leaves -wal, -shm and .lck siblings next to the database file. On Windows the
            // prefix match is what keeps the HoYoLab database in the same directory untouched.
            appDataDir.listFiles { file -> file.name.startsWith(databaseName) }?.forEach { it.delete() }
            // Only succeeds for the per-database directories used off Windows, and only once empty.
            appDataDir.delete()
        }
    }
}
