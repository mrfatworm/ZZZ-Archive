/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.russhwolf.settings.Settings
import database.DataBaseUseCase
import database.RoomDBFactory
import feature.agent.data.database.AgentsListDB
import feature.agent.data.repository.AgentRepository
import feature.agent.data.repository.AgentRepositoryImpl
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
import feature.banner.data.BannerRepository
import feature.banner.data.BannerRepositoryImpl
import feature.banner.domain.BannerUseCase
import feature.cover_image.data.CoverImageRepository
import feature.cover_image.data.CoverImageRepositoryImpl
import feature.cover_image.domain.CoverImageUseCase
import feature.drive.data.DriveRepository
import feature.drive.data.DriveRepositoryImpl
import feature.drive.domain.DrivesListUseCase
import feature.drive.presentation.DrivesListViewModel
import feature.home.presentation.HomeViewModel
import feature.news.data.OfficialNewsRepository
import feature.news.data.OfficialNewsRepositoryImpl
import feature.news.domain.OfficialNewsUseCase
import feature.pixiv.data.PixivRepository
import feature.pixiv.data.PixivRepositoryImpl
import feature.pixiv.domain.PixivUseCase
import feature.setting.data.GoogleDocRepository
import feature.setting.data.GoogleDocRepositoryImpl
import feature.setting.data.SettingsRepository
import feature.setting.data.SettingsRepositoryImpl
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.GoogleDocUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.LanguageUseCaseImpl
import feature.setting.domain.ThemeUseCase
import feature.setting.presentation.FeedbackViewModel
import feature.setting.presentation.SettingViewModel
import feature.splash.SplashViewModel
import feature.wengine.data.WEngineRepository
import feature.wengine.data.WEngineRepositoryImpl
import feature.wengine.domain.WEngineDetailUseCase
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.presentation.WEngineDetailViewModel
import feature.wengine.presentation.WEnginesListViewModel
import feature.wiki.presentation.WikiViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import root.MainContainerViewModel

expect val platformModule: Module

val sharedModule = module {
    single<Settings> { Settings() }
    single {
        get<RoomDBFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<AgentsListDB>().agentsListDao }

    // Repositories
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<OfficialNewsRepository> { OfficialNewsRepositoryImpl(get()) }
    single<PixivRepository> { PixivRepositoryImpl(get()) }
    single<CoverImageRepository> { CoverImageRepositoryImpl(get()) }
    single<AgentRepository> { AgentRepositoryImpl(get(), get()) }
    single<WEngineRepository> { WEngineRepositoryImpl(get()) }
    single<BangbooRepository> { BangbooRepositoryImpl(get()) }
    single<DriveRepository> { DriveRepositoryImpl(get()) }
    single<GoogleDocRepository> { GoogleDocRepositoryImpl(get()) }

    // Use cases
    single<CoverImageUseCase> { CoverImageUseCase(get()) }
    single<PixivUseCase> { PixivUseCase(get()) }
    single<BannerUseCase> { BannerUseCase(get()) }
    single<LanguageUseCase> { LanguageUseCaseImpl(get()) }
    single<OfficialNewsUseCase> { OfficialNewsUseCase(get(), get()) }
    single<AgentsListUseCase> { AgentsListUseCase(get()) }
    single<AgentDetailUseCase> { AgentDetailUseCase(get()) }
    single<BangbooListUseCase> { BangbooListUseCase(get()) }
    single<BangbooDetailUseCase> { BangbooDetailUseCase(get()) }
    single<WEnginesListUseCase> { WEnginesListUseCase(get()) }
    single<WEngineDetailUseCase> { WEngineDetailUseCase(get()) }
    single<DrivesListUseCase> { DrivesListUseCase(get()) }
    single<AppInfoUseCase> { AppInfoUseCase() }
    single<GoogleDocUseCase> { GoogleDocUseCase(get()) }
    single<ThemeUseCase> { ThemeUseCase(get()) }
    single<DataBaseUseCase> { DataBaseUseCase(get()) }

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