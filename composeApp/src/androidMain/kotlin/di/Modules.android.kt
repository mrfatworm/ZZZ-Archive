/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import database.RoomDatabaseFactory
import datastore.DataStoreFactory
import io.ktor.client.engine.okhttp.OkHttp
import network.ForumHttp
import network.ForumHttpImpl
import network.GoogleDocHttp
import network.GoogleDocHttpImpl
import network.HoYoLabHttp
import network.HoYoLabHttpImpl
import network.OfficialWebHttp
import network.OfficialWebHttpImpl
import network.PixivHttp
import network.PixivHttpImpl
import network.ZzzHttp
import network.ZzzHttpImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import utils.AppActionsUseCase

actual val platformModule = module {
    singleOf(::AppActionsUseCase)
    singleOf(::RoomDatabaseFactory)
    singleOf(::DataStoreFactory)
    single<ZzzHttp> { ZzzHttpImpl(OkHttp.create()) }
    single<OfficialWebHttp> { OfficialWebHttpImpl(OkHttp.create()) }
    single<PixivHttp> { PixivHttpImpl(OkHttp.create()) }
    single<GoogleDocHttp> { GoogleDocHttpImpl(OkHttp.create()) }
    single<HoYoLabHttp> { HoYoLabHttpImpl(OkHttp.create()) }
    single<ForumHttp> { ForumHttpImpl(OkHttp.create()) }
}