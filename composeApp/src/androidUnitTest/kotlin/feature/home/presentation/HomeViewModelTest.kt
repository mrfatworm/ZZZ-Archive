/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation


import MainDispatcherRule
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsListResponse
import feature.bangboo.data.FakeBangbooRepository
import feature.bangboo.model.stubBangbooListResponse
import feature.cover.data.FakeCoverImageRepository
import feature.cover.data.stubCoverImageResponse
import feature.drive.data.FakeDriveRepository
import feature.drive.model.stubDrivesListResponse
import feature.news.domain.FakeOfficialNewsUseCase
import feature.news.presentation.stubOfficialNewsState
import feature.pixiv.data.FakePixivRepository
import feature.pixiv.data.stubPixivTopicResponse
import feature.setting.data.FakeSettingRepository
import feature.wengine.data.FakeWEngineRepository
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
    private val wEngineRepository = FakeWEngineRepository()
    private val bangbooRepository = FakeBangbooRepository()
    private val driveRepository = FakeDriveRepository()
    private val settingsRepository = FakeSettingRepository()
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        coEvery { agentsListUseCase.invoke() } returns Result.success(stubAgentsListResponse.agents)
        viewModel = HomeViewModel(
            bannerRepository,
            imageBannerRepository,
            pixivRepository,
            officialNewsUseCase,
            agentsListUseCase,
            wEngineRepository,
            bangbooRepository,
            driveRepository,
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