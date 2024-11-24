/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import com.russhwolf.settings.Settings
import feature.agent.data.AgentRepository
import feature.agent.data.AgentRepositoryImpl
import feature.agent.domain.AgentDetailUseCase
import feature.agent.domain.AgentsListUseCase
import feature.agent.presentation.AgentDetailViewModel
import feature.agent.presentation.AgentsListViewModel
import feature.bangboo.data.BangbooRepository
import feature.bangboo.data.BangbooRepositoryImpl
import feature.bangboo.domain.BangbooDetailUseCase
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.presentation.BangbooDetailViewModel
import feature.bangboo.presentation.BangbooListViewModel
import feature.cover.data.CoverImageRepository
import feature.cover.data.CoverImageRepositoryImpl
import feature.drive.data.DriveRepository
import feature.drive.data.DriveRepositoryImpl
import feature.drive.domain.DrivesListViewModel
import feature.home.presentation.HomeViewModel
import feature.news.data.OfficialNewsRepository
import feature.news.data.OfficialNewsRepositoryImpl
import feature.news.domain.OfficialNewsUseCase
import feature.news.domain.OfficialNewsUseCaseImpl
import feature.pixiv.data.PixivRepository
import feature.pixiv.data.PixivRepositoryImpl
import feature.setting.data.AppInfoRepository
import feature.setting.data.AppInfoRepositoryImpl
import feature.setting.data.GoogleDocRepository
import feature.setting.data.GoogleDocRepositoryImpl
import feature.setting.data.SettingsRepository
import feature.setting.data.SettingsRepositoryImpl
import feature.setting.domain.FeedbackViewModel
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.LanguageUseCaseImpl
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

expect val platformModule: Module

val sharedModule = module {
    single<Settings> { Settings() }

    // Repositories
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<AppInfoRepository> { AppInfoRepositoryImpl() }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<OfficialNewsRepository> { OfficialNewsRepositoryImpl(get()) }
    single<PixivRepository> { PixivRepositoryImpl(get()) }
    single<CoverImageRepository> { CoverImageRepositoryImpl(get()) }
    single<AgentRepository> { AgentRepositoryImpl(get()) }
    single<WEngineRepository> { WEngineRepositoryImpl(get()) }
    single<BangbooRepository> { BangbooRepositoryImpl(get()) }
    single<DriveRepository> { DriveRepositoryImpl(get()) }
    single<GoogleDocRepository> { GoogleDocRepositoryImpl(get()) }

    // Use cases
    single<LanguageUseCase> { LanguageUseCaseImpl(get()) }
    single<OfficialNewsUseCase> { OfficialNewsUseCaseImpl(get(), get()) }
    single<AgentsListUseCase> { AgentsListUseCase(get()) }
    single<AgentDetailUseCase> { AgentDetailUseCase(get()) }
    single<BangbooListUseCase> { BangbooListUseCase(get()) }
    single<BangbooDetailUseCase> { BangbooDetailUseCase(get()) }


    // ViewModels
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