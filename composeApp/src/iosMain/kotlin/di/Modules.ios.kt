/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import database.RoomDBFactory
import io.ktor.client.engine.darwin.Darwin
import network.GoogleDocHttp
import network.GoogleDocHttpImpl
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
    singleOf(::RoomDBFactory)
    single<ZzzHttp> { ZzzHttpImpl(Darwin.create(), get()) }
    single<OfficialWebHttp> { OfficialWebHttpImpl(Darwin.create(), get()) }
    single<PixivHttp> { PixivHttpImpl(Darwin.create()) }
    single<GoogleDocHttp> { GoogleDocHttpImpl(Darwin.create()) }
}