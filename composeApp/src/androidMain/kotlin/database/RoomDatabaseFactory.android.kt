/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import feature.hoyolab.data.database.HoYoLabAccountDB
import java.io.File

actual class RoomDatabaseFactory(private val context: Context) {
    private fun <T : RoomDatabase> createDB(
        databaseClass: Class<T>,
        databaseName: String
    ): RoomDatabase.Builder<T> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(databaseName)

        return Room.databaseBuilder(
            context = appContext,
            klass = databaseClass,
            name = dbFile.absolutePath
        )
    }

    actual fun createCacheDatabase(): RoomDatabase.Builder<ZzzCacheDB> =
        createDB(ZzzCacheDB::class.java, ZzzCacheDB.DATABASE_NAME)

    actual fun createHoYoLabAccountDatabase(): RoomDatabase.Builder<HoYoLabAccountDB> =
        createDB(HoYoLabAccountDB::class.java, HoYoLabAccountDB.DATABASE_NAME)

    actual fun deleteLegacyCacheDatabases() {
        val appContext = context.applicationContext
        ZzzCacheDB.LEGACY_DATABASE_NAMES.forEach { databaseName ->
            // deleteDatabase takes the -wal / -shm / -journal siblings with it, but not the .lck
            // file Room's own FileLock leaves next to the database.
            appContext.deleteDatabase(databaseName)
            File("${appContext.getDatabasePath(databaseName).absolutePath}.lck").delete()
        }
    }
}
