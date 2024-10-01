/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package di

import app.agent.data.AgentRepository
import app.agent.data.AgentRepositoryImpl
import app.bangboo.data.BangbooRepository
import app.bangboo.data.BangbooRepositoryImpl
import app.drive.data.DriveRepository
import app.drive.data.DriveRepositoryImpl
import app.wengine.data.WEngineRepositoryImpl
import app.home.data.BannerRepository
import app.home.data.BannerRepositoryImpl
import app.home.data.NewsRepository
import app.home.data.NewsRepositoryImpl
import app.home.data.PixivRepository
import app.home.data.PixivRepositoryImpl
import app.home.domain.HomeViewModel
import app.wengine.data.WEngineRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    single<PixivRepository> { PixivRepositoryImpl(get()) }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<AgentRepository> { AgentRepositoryImpl(get()) }
    single<WEngineRepository> { WEngineRepositoryImpl(get()) }
    single<BangbooRepository> { BangbooRepositoryImpl(get()) }
    single<DriveRepository> { DriveRepositoryImpl(get()) }

    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get()) }
}