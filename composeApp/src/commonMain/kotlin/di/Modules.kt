/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package di

import app.agent.data.AgentRepository
import app.agent.data.AgentRepositoryImpl
import app.agent.domain.AgentListViewModel
import app.bangboo.data.BangbooRepository
import app.bangboo.data.BangbooRepositoryImpl
import app.drive.data.DriveRepository
import app.drive.data.DriveRepositoryImpl
import app.home.data.ImageBannerRepository
import app.home.data.ImageBannerRepositoryImpl
import app.home.data.NewsRepository
import app.home.data.NewsRepositoryImpl
import app.home.data.PixivRepository
import app.home.data.PixivRepositoryImpl
import app.home.domain.HomeViewModel
import app.splash.SplashViewModel
import app.wengine.data.WEngineRepository
import app.wengine.data.WEngineRepositoryImpl
import com.russhwolf.settings.Settings
import mainfunc.MainFuncViewModel
import mainfunc.data.BannerRepository
import mainfunc.data.BannerRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import setting.SettingsRepository
import setting.SettingsRepositoryImpl

expect val platformModule: Module

val sharedModule = module {
    single<Settings> { Settings() }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    single<PixivRepository> { PixivRepositoryImpl(get()) }
    single<ImageBannerRepository> { ImageBannerRepositoryImpl(get()) }
    single<AgentRepository> { AgentRepositoryImpl(get()) }
    single<WEngineRepository> { WEngineRepositoryImpl(get()) }
    single<BangbooRepository> { BangbooRepositoryImpl(get()) }
    single<DriveRepository> { DriveRepositoryImpl(get()) }

    viewModel { SplashViewModel(get()) }
    viewModel { MainFuncViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { AgentListViewModel(get()) }
}