package di

import feature.agent.data.repository.AgentRepository
import feature.agent.data.repository.AgentRepositoryImpl
import feature.banner.data.BannerRepository
import feature.banner.data.BannerRepositoryImpl
import feature.cover.data.repository.CoverImageRepository
import feature.cover.data.repository.CoverImageRepositoryImpl
import feature.feedback.data.GoogleDocRepository
import feature.feedback.data.GoogleDocRepositoryImpl
import feature.forum.data.ForumRepository
import feature.forum.data.ForumRepositoryImpl
import feature.home.data.AssetVersionRepository
import feature.home.data.AssetVersionRepositoryImpl
import feature.hoyolab.data.credential.HoYoLabCredentialStore
import feature.hoyolab.data.credential.KSafeHoYoLabCredentialStore
import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.crypto.ZzzCryptoImpl
import feature.hoyolab.data.repository.HoYoLabAgentRepository
import feature.hoyolab.data.repository.HoYoLabAgentRepositoryImpl
import feature.hoyolab.data.repository.HoYoLabConfigRepository
import feature.hoyolab.data.repository.HoYoLabConfigRepositoryImpl
import feature.news.data.OfficialNewsRepository
import feature.news.data.OfficialNewsRepositoryImpl
import feature.pixiv.data.PixivRepository
import feature.pixiv.data.PixivRepositoryImpl
import feature.setting.data.PreferencesRepository
import feature.setting.data.PreferencesRepositoryImpl
import feature.setting.data.SystemConfigRepository
import feature.setting.data.SystemConfigRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<SystemConfigRepository> { SystemConfigRepositoryImpl(get(named("ConfigDataStore"))) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get(named("PreferenceDataStore"))) }
    single<AssetVersionRepository> { AssetVersionRepositoryImpl(get()) }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<OfficialNewsRepository> { OfficialNewsRepositoryImpl(get()) }
    single<PixivRepository> { PixivRepositoryImpl(get()) }
    single<CoverImageRepository> { CoverImageRepositoryImpl(get(), get()) }
    single<AgentRepository> { AgentRepositoryImpl(get(), get()) }
    single<GoogleDocRepository> { GoogleDocRepositoryImpl(get()) }
    single<HoYoLabConfigRepository> { HoYoLabConfigRepositoryImpl(get(), get()) }
    single<HoYoLabAgentRepository> { HoYoLabAgentRepositoryImpl(get()) }
    single<ForumRepository> { ForumRepositoryImpl(get()) }
    single<HoYoLabCredentialStore> { KSafeHoYoLabCredentialStore(get()) }
    // Read-only from here on: it exists only so HoYoLabCredentialMigrationUseCase can open the
    // rows written before the credentials moved to the platform key store.
    single<ZzzCrypto> { ZzzCryptoImpl() }
}
