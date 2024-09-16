/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package di

import database.ZzzDatabase
import io.ktor.client.engine.okhttp.OkHttp
import network.ZzzHttpClient
import network.ZzzHttpClientImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ZzzDatabase)
    single { ZzzHttpClientImpl(OkHttp.create()) }.bind<ZzzHttpClient>()
}