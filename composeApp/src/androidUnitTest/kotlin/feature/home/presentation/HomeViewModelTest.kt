/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation


import MainDispatcherRule
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsListResponse
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.stubBangbooListResponse
import feature.cover.data.FakeCoverImageRepository
import feature.cover.data.stubCoverImageResponse
import feature.drive.domain.DrivesListUseCase
import feature.drive.model.stubDrivesListResponse
import feature.news.domain.FakeOfficialNewsUseCase
import feature.news.presentation.stubOfficialNewsState
import feature.pixiv.data.FakePixivRepository
import feature.pixiv.data.stubPixivTopicResponse
import feature.setting.data.FakeSettingRepository
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesListResponse
import io.mockk.coEvery
import io.mockk.mockk
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
    private val imageBannerRepository = FakeCoverImageRepository()
    private val pixivRepository = FakePixivRepository()
    private val officialNewsUseCase = FakeOfficialNewsUseCase()
    private val agentsListUseCase = mockk<AgentsListUseCase>()
    private val wEngineListUseCase = mockk<WEnginesListUseCase>()
    private val bangbooListUseCase = mockk<BangbooListUseCase>()
    private val drivesListUseCase = mockk<DrivesListUseCase>()
    private val settingsRepository = FakeSettingRepository()
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        coEvery { agentsListUseCase.invoke() } returns Result.success(stubAgentsListResponse.agents)
        coEvery { wEngineListUseCase.invoke() } returns Result.success(stubWEnginesListResponse.wEngines)
        coEvery { bangbooListUseCase.invoke() } returns Result.success(stubBangbooListResponse.bangboo)
        coEvery { drivesListUseCase.invoke() } returns Result.success(stubDrivesListResponse.drives)
        viewModel = HomeViewModel(
            bannerRepository,
            imageBannerRepository,
            pixivRepository,
            officialNewsUseCase,
            agentsListUseCase,
            wEngineListUseCase,
            bangbooListUseCase,
            drivesListUseCase,
            settingsRepository,
        )
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.banner, stubBannerResponse)
        assertEquals(state.imageBanner, stubCoverImageResponse)
        assertEquals(state.pixivPuppiesList, stubPixivTopicResponse.getPopularArticles())
        assertEquals(state.newsList.first(), stubOfficialNewsState)
        assertEquals(state.agentsList, stubAgentsListResponse.agents)
        assertEquals(state.wEnginesList, stubWEnginesListResponse.wEngines)
        assertEquals(state.bangbooList, stubBangbooListResponse.bangboo)
        assertEquals(state.drivesList, stubDrivesListResponse.drives)
    }

    @Test
    fun `Set Banner Ignore Id as One than ignore banner data`() {
        viewModel.closeBannerAndIgnoreId(1)
        val state = viewModel.uiState.value
        assertEquals(state.banner, null)
    }
}