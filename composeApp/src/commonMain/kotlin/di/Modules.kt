/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.russhwolf.settings.Settings
import database.RoomDatabaseFactory
import database.UpdateDatabaseUseCase
import feature.agent.data.database.AgentsListDB
import feature.agent.data.repository.AgentRepository
import feature.agent.data.repository.AgentRepositoryImpl
import feature.agent.domain.AgentDetailUseCase
import feature.agent.domain.AgentsListUseCase
import feature.agent.presentation.AgentDetailViewModel
import feature.agent.presentation.AgentsListViewModel
import feature.bangboo.data.database.BangbooListDB
import feature.bangboo.data.repository.BangbooRepository
import feature.bangboo.data.repository.BangbooRepositoryImpl
import feature.bangboo.domain.BangbooDetailUseCase
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.presentation.BangbooDetailViewModel
import feature.bangboo.presentation.BangbooListViewModel
import feature.banner.data.BannerRepository
import feature.banner.data.BannerRepositoryImpl
import feature.banner.domain.BannerUseCase
import feature.cover_image.data.database.CoverImagesListDB
import feature.cover_image.data.repository.CoverImageRepository
import feature.cover_image.data.repository.CoverImageRepositoryImpl
import feature.cover_image.domain.CoverImageUseCase
import feature.drive.data.database.DrivesListDB
import feature.drive.data.respository.DriveRepository
import feature.drive.data.respository.DriveRepositoryImpl
import feature.drive.domain.DrivesListUseCase
import feature.drive.presentation.DrivesListViewModel
import feature.home.data.AssetVersionRepository
import feature.home.data.AssetVersionRepositoryImpl
import feature.home.presentation.HomeViewModel
import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.crypto.ZzzCryptoImpl
import feature.hoyolab.data.database.HoYoLabAccountDB
import feature.hoyolab.data.repository.HoYoLabRepository
import feature.hoyolab.data.repository.HoYoLabRepositoryImpl
import feature.hoyolab.domain.HoYoLabManageUseCase
import feature.hoyolab.presentation.HoYoLabConnectViewModel
import feature.news.data.OfficialNewsRepository
import feature.news.data.OfficialNewsRepositoryImpl
import feature.news.domain.OfficialNewsUseCase
import feature.pixiv.data.PixivRepository
import feature.pixiv.data.PixivRepositoryImpl
import feature.pixiv.domain.PixivUseCase
import feature.setting.data.GoogleDocRepository
import feature.setting.data.GoogleDocRepositoryImpl
import feature.setting.data.PreferencesRepository
import feature.setting.data.PreferencesRepositoryImpl
import feature.setting.data.SystemConfigRepository
import feature.setting.data.SystemConfigRepositoryImpl
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.GoogleDocUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.LanguageUseCaseImpl
import feature.setting.domain.ThemeUseCase
import feature.setting.domain.UiScaleUseCase
import feature.setting.presentation.FeedbackViewModel
import feature.setting.presentation.SettingViewModel
import feature.splash.SplashViewModel
import feature.wengine.data.database.WEnginesListDB
import feature.wengine.data.repository.WEngineRepository
import feature.wengine.data.repository.WEngineRepositoryImpl
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

    // Database
    single {
        get<RoomDatabaseFactory>().createAgentListDatabase().setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<RoomDatabaseFactory>().createWEnginesListDatabase().setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<RoomDatabaseFactory>().createBangbooListDatabase().setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<RoomDatabaseFactory>().createDrivesListDatabase().setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<RoomDatabaseFactory>().createCoverImagesListDatabase().setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<RoomDatabaseFactory>().createHoYoLabAccountDatabase().setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<AgentsListDB>().agentsListDao }
    single { get<WEnginesListDB>().wEnginesListDao }
    single { get<BangbooListDB>().bangbooListDao }
    single { get<DrivesListDB>().drivesListDao }
    single { get<CoverImagesListDB>().coverImagesListDao }
    single { get<HoYoLabAccountDB>().hoYoLabAccountDao }

    // Repositories
    single<SystemConfigRepository> { SystemConfigRepositoryImpl(get()) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
    single<AssetVersionRepository> { AssetVersionRepositoryImpl(get()) }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<OfficialNewsRepository> { OfficialNewsRepositoryImpl(get()) }
    single<PixivRepository> { PixivRepositoryImpl(get()) }
    single<CoverImageRepository> { CoverImageRepositoryImpl(get(), get()) }
    single<AgentRepository> { AgentRepositoryImpl(get(), get()) }
    single<WEngineRepository> { WEngineRepositoryImpl(get(), get()) }
    single<BangbooRepository> { BangbooRepositoryImpl(get(), get()) }
    single<DriveRepository> { DriveRepositoryImpl(get(), get()) }
    single<GoogleDocRepository> { GoogleDocRepositoryImpl(get()) }
    single<HoYoLabRepository> { HoYoLabRepositoryImpl(get(), get()) }
    single<ZzzCrypto> { ZzzCryptoImpl() }

    // Use cases
    single<CoverImageUseCase> { CoverImageUseCase(get()) }
    single<PixivUseCase> { PixivUseCase(get()) }
    single<BannerUseCase> { BannerUseCase(get(), get()) }
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
    single<UpdateDatabaseUseCase> {
        UpdateDatabaseUseCase(get(), get(), get(), get(), get(), get(), get())
    }
    single<UiScaleUseCase> { UiScaleUseCase(get()) }
    single<HoYoLabManageUseCase> { HoYoLabManageUseCase(get(), get()) }

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
    viewModelOf(::HoYoLabConnectViewModel)
}