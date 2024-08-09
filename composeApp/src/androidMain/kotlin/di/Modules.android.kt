/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package di

import database.ZzzDatabase
import home.domain.ZzzViewModel
import io.ktor.client.engine.okhttp.OkHttp
import network.ZzzHttpClient
import network.ZzzHttpClientImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ZzzDatabase)
    single { ZzzHttpClientImpl(OkHttp.create()) }.bind<ZzzHttpClient>()
    viewModelOf(::ZzzViewModel)
}