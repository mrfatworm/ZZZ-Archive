package di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.RoomDatabaseFactory
import database.ZzzCacheDB
import feature.hoyolab.data.database.HoYoLabAccountDB
import org.koin.dsl.module

val databaseModule = module {
    single {
        val factory = get<RoomDatabaseFactory>()
        // Installs that predate ZzzCacheDB still carry the two databases it replaced. Drop this
        // once those versions are out of circulation.
        factory.deleteLegacyCacheDatabases()
        factory
            .createCacheDatabase()
            .setDriver(BundledSQLiteDriver())
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single {
        get<RoomDatabaseFactory>()
            .createHoYoLabAccountDatabase()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<ZzzCacheDB>().agentsListDao }
    single { get<ZzzCacheDB>().coverImagesListDao }
    single { get<HoYoLabAccountDB>().hoYoLabAccountDao }
}
