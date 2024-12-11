/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import database.RoomDatabaseFactory
import io.ktor.client.engine.okhttp.OkHttp
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
    single<ZzzHttp> { ZzzHttpImpl(OkHttp.create(), get()) }
    single<OfficialWebHttp> { OfficialWebHttpImpl(OkHttp.create(), get()) }
    single<PixivHttp> { PixivHttpImpl(OkHttp.create()) }
    single<GoogleDocHttp> { GoogleDocHttpImpl(OkHttp.create()) }
    single<HoYoLabHttp> { HoYoLabHttpImpl(OkHttp.create()) }
}