/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import com.russhwolf.settings.Settings
import feature.agent.data.AgentRepository
import feature.agent.data.AgentRepositoryImpl
import feature.agent.domain.AgentDetailViewModel
import feature.agent.domain.AgentsListViewModel
import feature.bangboo.data.BangbooRepository
import feature.bangboo.data.BangbooRepositoryImpl
import feature.bangboo.domain.BangbooDetailViewModel
import feature.bangboo.domain.BangbooListViewModel
import feature.drive.data.DriveRepository
import feature.drive.data.DriveRepositoryImpl
import feature.drive.domain.DrivesListViewModel
import feature.home.data.ImageBannerRepository
import feature.home.data.ImageBannerRepositoryImpl
import feature.home.data.NewsRepository
import feature.home.data.NewsRepositoryImpl
import feature.home.data.PixivRepository
import feature.home.data.PixivRepositoryImpl
import feature.home.domain.HomeViewModel
import feature.setting.data.AppInfoRepository
import feature.setting.data.AppInfoRepositoryImpl
import feature.setting.data.GoogleDocRepository
import feature.setting.data.GoogleDocRepositoryImpl
import feature.setting.data.SettingsRepository
import feature.setting.data.SettingsRepositoryImpl
import feature.setting.domain.FeedbackViewModel
import feature.setting.domain.SettingViewModel
import feature.splash.SplashViewModel
import feature.wengine.data.WEngineRepository
import feature.wengine.data.WEngineRepositoryImpl
import feature.wengine.domain.WEngineDetailViewModel
import feature.wengine.domain.WEnginesListViewModel
import feature.wiki.domain.WikiViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import root.MainContainerViewModel
import root.data.BannerRepository
import root.data.BannerRepositoryImpl
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
    viewModelOf(::MainContainerViewModel)
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