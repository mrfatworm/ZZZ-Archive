/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.home.domain


import MainDispatcherRule
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentsListResponse
import app.bangboo.data.FakeBangbooRepository
import app.bangboo.model.stubBangbooListResponse
import app.drive.data.FakeDriveRepository
import app.drive.model.stubDrivesListResponse
import app.home.data.FakeImageBannerRepository
import app.home.data.FakeNewsRepository
import app.home.data.FakePixivRepository
import app.home.model.stubImageBannerResponse
import app.home.model.stubOfficialNewsDataResponse
import app.home.model.stubPixivZzzTopic
import app.setting.data.FakeSettingRepository
import app.wengine.data.FakeWEngineRepository
import app.wengine.model.stubWEnginesListResponse
import mainfunc.data.FakeBannerRepository
import mainfunc.model.stubBannerResponse
import org.junit.Rule
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