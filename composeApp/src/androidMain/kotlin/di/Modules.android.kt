/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package di

import database.ZzzDatabase
import io.ktor.client.engine.okhttp.OkHttp
import network.OfficialWebHttp
import network.OfficialWebHttpImpl
import network.PixivHttp
import network.PixivHttpImpl
import network.ZzzHttp
import network.ZzzHttpImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ZzzDatabase)
    single<ZzzHttp> { ZzzHttpImpl(OkHttp.create(), get()) }
    single<OfficialWebHttp> { OfficialWebHttpImpl(OkHttp.create(), get()) }
    single<PixivHttp> { PixivHttpImpl(OkHttp.create()) }
}