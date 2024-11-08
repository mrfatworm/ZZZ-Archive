/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import app.agent.data.AgentRepository
import app.agent.data.AgentRepositoryImpl
import app.agent.domain.AgentDetailViewModel
import app.agent.domain.AgentsListViewModel
import app.bangboo.data.BangbooRepository
import app.bangboo.data.BangbooRepositoryImpl
import app.bangboo.domain.BangbooDetailViewModel
import app.bangboo.domain.BangbooListViewModel
import app.drive.data.DriveRepository
import app.drive.data.DriveRepositoryImpl
import app.drive.domain.DrivesListViewModel
import app.home.data.ImageBannerRepository
import app.home.data.ImageBannerRepositoryImpl
import app.home.data.NewsRepository
import app.home.data.NewsRepositoryImpl
import app.home.data.PixivRepository
import app.home.data.PixivRepositoryImpl
import app.home.domain.HomeViewModel
import app.setting.data.AppInfoRepository
import app.setting.data.AppInfoRepositoryImpl
import app.setting.data.GoogleDocRepository
import app.setting.data.GoogleDocRepositoryImpl
import app.setting.data.SettingsRepository
import app.setting.data.SettingsRepositoryImpl
import app.setting.domain.FeedbackViewModel
import app.setting.domain.SettingViewModel
import app.splash.SplashViewModel
import app.wengine.data.WEngineRepository
import app.wengine.data.WEngineRepositoryImpl
import app.wengine.domain.WEngineDetailViewModel
import app.wengine.domain.WEnginesListViewModel
import app.wiki.domain.WikiViewModel
import com.russhwolf.settings.Settings
import mainfunc.MainFuncViewModel
import mainfunc.data.BannerRepository
import mainfunc.data.BannerRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import utils.LanguageHandler
import utils.LanguageHandlerImpl

expect val platformModule: Module

val sharedModule = module {
    single<Settings> { Settings() }
    single<LanguageHandler> { LanguageHandlerImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<AppInfoRepository> { AppInfoRepositoryImpl() }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    single<PixivRepository> { PixivRepositoryImpl(get()) }
    single<ImageBannerRepository> { ImageBannerRepositoryImpl(get()) }
    single<AgentRepository> { AgentRepositoryImpl(get()) }
    single<WEngineRepository> { WEngineRepositoryImpl(get()) }
    single<BangbooRepository> { BangbooRepositoryImpl(get()) }
    single<DriveRepository> { DriveRepositoryImpl(get()) }
    single<GoogleDocRepository> { GoogleDocRepositoryImpl(get()) }


    viewModelOf(::SplashViewModel)
    viewModelOf(::MainFuncViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::WikiViewModel)
    viewModelOf(::AgentsListViewModel)
    viewModelOf(::AgentDetailViewModel)
    viewModelOf(::WEnginesListViewModel)
    viewModelOf(::WEngineDetailViewModel)
    viewModelOf(::BangbooListViewModel)
    viewModelOf(::BangbooDetailViewModel)
    viewModelOf(::DrivesListViewModel)
    viewModelOf(::SettingViewModel)
    viewModelOf(::FeedbackViewModel)
}