/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.domain


import MainDispatcherRule
import feature.agent.data.FakeAgentRepository
import feature.agent.model.stubAgentsListResponse
import feature.bangboo.data.FakeBangbooRepository
import feature.bangboo.model.stubBangbooListResponse
import feature.drive.data.FakeDriveRepository
import feature.drive.model.stubDrivesListResponse
import feature.home.data.FakeImageBannerRepository
import feature.home.data.FakeNewsRepository
import feature.home.data.FakePixivRepository
import feature.home.model.stubImageBannerResponse
import feature.home.model.stubOfficialNewsDataResponse
import feature.home.model.stubPixivZzzTopic
import feature.setting.data.FakeSettingRepository
import feature.wengine.data.FakeWEngineRepository
import feature.wengine.model.stubWEnginesListResponse
import org.junit.Rule
import root.data.FakeBannerRepository
import root.model.stubBannerResponse
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bannerRepository = FakeBannerRepository()
    private val imageBannerRepository = FakeImageBannerRepository()
    private val pixivRepository = FakePixivRepository()
    private val newsRepository = FakeNewsRepository()
    private val agentRepository = FakeAgentRepository()
    private val wEngineRepository = FakeWEngineRepository()
    private val bangbooRepository = FakeBangbooRepository()
    private val driveRepository = FakeDriveRepository()
    private val settingsRepository = FakeSettingRepository()
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setup() {
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
        assertEquals(state.drivesList, stubDrivesListResponse.getDrivesNewToOld())
    }

    @Test
    fun `Set Banner Ignore Id as One than ignore banner data`() {
        viewModel.closeBannerAndIgnoreId(1)
        val state = viewModel.uiState.value
        assertEquals(state.banner, null)
    }
}