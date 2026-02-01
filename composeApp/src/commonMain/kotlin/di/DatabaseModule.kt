package di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.RoomDatabaseFactory
import feature.agent.data.database.AgentsListDB
import feature.cover.data.database.CoverImagesListDB
import feature.hoyolab.data.database.HoYoLabAccountDB
import org.koin.dsl.module

val databaseModule = module {
    single {
        get<RoomDatabaseFactory>()
            .createAgentListDatabase()
            .setDriver(BundledSQLiteDriver())
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single {
        get<RoomDatabaseFactory>()
            .createCoverImagesListDatabase()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<RoomDatabaseFactory>()
            .createHoYoLabAccountDatabase()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<AgentsListDB>().agentsListDao }
    single { get<CoverImagesListDB>().coverImagesListDao }
    single { get<HoYoLabAccountDB>().hoYoLabAccountDao }
}
