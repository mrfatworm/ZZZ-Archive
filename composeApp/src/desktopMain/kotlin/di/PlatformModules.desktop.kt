/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import database.RoomDatabaseFactory
import datastore.DataStoreFactory
import eu.anifantakis.lib.ksafe.KSafe
import eu.anifantakis.lib.ksafe.KSafeConfig
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
import utils.AppActionsUseCaseImpl

actual val platformModule =
    module {
        single<AppActionsUseCase> { AppActionsUseCaseImpl() }
        singleOf(::RoomDatabaseFactory)
        singleOf(::DataStoreFactory)
        // The desktop OS secret store is shared by everything the user runs, so the key slot has
        // to be namespaced by hand; Android and iOS separate apps for us and ignore the field.
        single { KSafe(config = KSafeConfig(appNamespace = "com.mrfatworm.zzzarchive")) }
        single<ZzzHttp> { ZzzHttpImpl(OkHttp.create()) }
        single<OfficialWebHttp> { OfficialWebHttpImpl(OkHttp.create()) }
        single<PixivHttp> { PixivHttpImpl(OkHttp.create()) }
        single<GoogleDocHttp> { GoogleDocHttpImpl(OkHttp.create()) }
        single<HoYoLabHttp> { HoYoLabHttpImpl(OkHttp.create()) }
        single<ForumHttp> { ForumHttpImpl(OkHttp.create()) }
    }
