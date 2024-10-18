/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.domain


import MainDispatcherRule
import app.agent.data.AgentRepository
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentsListResponse
import app.bangboo.data.BangbooRepository
import app.bangboo.data.FakeBangbooRepository
import app.bangboo.model.stubBangbooListResponse
import app.drive.data.DriveRepository
import app.drive.data.FakeDriveRepository
import app.drive.model.stubDriveListResponse
import app.home.data.FakeImageBannerRepository
import app.home.data.FakeNewsRepository
import app.home.data.FakePixivRepository
import app.home.data.ImageBannerRepository
import app.home.data.NewsRepository
import app.home.data.PixivRepository
import app.home.model.stubImageBannerResponse
import app.home.model.stubOfficialNewsDataResponse
import app.home.model.stubPixivZzzTopic
import app.wengine.data.FakeWEngineRepository
import app.wengine.data.WEngineRepository
import app.wengine.model.stubWEnginesListResponse
import mainfunc.data.BannerRepository
import mainfunc.data.FakeBannerRepository
import mainfunc.model.stubBannerResponse
import org.junit.Rule
import setting.FakeSettingRepository
import setting.SettingsRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var bannerRepository: BannerRepository
    private lateinit var imageBannerRepository: ImageBannerRepository
    private lateinit var pixivRepository: PixivRepository
    private lateinit var newsRepository: NewsRepository
    private lateinit var agentRepository: AgentRepository
    private lateinit var wEngineRepository: WEngineRepository
    private lateinit var bangbooRepository: BangbooRepository
    private lateinit var driveRepository: DriveRepository
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        bannerRepository = FakeBannerRepository()
        imageBannerRepository = FakeImageBannerRepository()
        pixivRepository = FakePixivRepository()
        newsRepository = FakeNewsRepository()
        agentRepository = FakeAgentRepository()
        wEngineRepository = FakeWEngineRepository()
        bangbooRepository = FakeBangbooRepository()
        driveRepository = FakeDriveRepository()
        settingsRepository = FakeSettingRepository()
        viewModel = HomeViewModel(
            bannerRepository,
            imageBannerRepository,
            pixivRepository,
            newsRepository,
            agentRepository,
            wEngineRepository,
            bangbooRepository,
            driveRepository,
            settingsRepository
        )
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.banner, stubBannerResponse)
        assertEquals(state.imageBanner, stubImageBannerResponse)
        assertEquals(state.pixivPuppiesList, stubPixivZzzTopic.getPopularArticles())
        assertEquals(state.news, stubOfficialNewsDataResponse)
        assertEquals(state.agentsList, stubAgentsListResponse.getAgentsNewToOld())
        assertEquals(state.wEnginesList, stubWEnginesListResponse.getWEnginesNewToOld())
        assertEquals(state.bangbooList, stubBangbooListResponse.getBangbooNewToOld())
        assertEquals(state.drivesList, stubDriveListResponse.getDrivesNewToOld())
    }

    @Test
    fun `Set Banner Ignore Id as One than ignore banner data`() {
        viewModel.closeBannerAndIgnoreId(1)
        val state = viewModel.uiState.value
        assertEquals(state.banner, null)
    }
}