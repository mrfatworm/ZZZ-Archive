/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation


import MainDispatcherRule
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsList
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.stubBangbooListResponse
import feature.banner.data.stubBannerResponse
import feature.banner.domain.BannerUseCase
import feature.cover_image.domain.CoverImageUseCase
import feature.cover_image.model.stubCoverImageResponse
import feature.drive.domain.DrivesListUseCase
import feature.drive.model.stubDrivesListResponse
import feature.news.data.stubOfficialNewsDataResponse
import feature.news.domain.OfficialNewsUseCase
import feature.news.presentation.stubOfficialNewsState
import feature.pixiv.data.stubPixivTopicResponse
import feature.pixiv.domain.PixivUseCase
import feature.setting.data.FakeSettingRepository
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesListResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bannerUseCase = mockk<BannerUseCase>()
    private val coverImageUseCase = mockk<CoverImageUseCase>()
    private val pixivUseCase = mockk<PixivUseCase>()
    private val officialNewsUseCase = mockk<OfficialNewsUseCase>()
    private val agentsListUseCase = mockk<AgentsListUseCase>()
    private val wEngineListUseCase = mockk<WEnginesListUseCase>()
    private val bangbooListUseCase = mockk<BangbooListUseCase>()
    private val drivesListUseCase = mockk<DrivesListUseCase>()
    private val settingsRepository = FakeSettingRepository()
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        coEvery { bannerUseCase.invoke() } returns Result.success(stubBannerResponse)
        coEvery { coverImageUseCase.invoke() } returns Result.success(stubCoverImageResponse)
        coEvery { pixivUseCase.invoke(any()) } returns Result.success(stubPixivTopicResponse.getPopularArticles())
        coEvery {
            officialNewsUseCase.getNewsPeriodically(
                any(), any()
            )
        } returns flowOf(Result.success(stubOfficialNewsDataResponse.data.list))
        every { officialNewsUseCase.convertToOfficialNewsState(any()) } returns listOf(
            stubOfficialNewsState
        )
        coEvery { agentsListUseCase.invoke() } returns flowOf(stubAgentsList)
        coEvery { agentsListUseCase.updateAgentsList() } returns Result.success(Unit)
        coEvery { wEngineListUseCase.invoke() } returns Result.success(stubWEnginesListResponse.wEngines)
        coEvery { bangbooListUseCase.invoke() } returns Result.success(stubBangbooListResponse.bangboo)
        coEvery { drivesListUseCase.invoke() } returns Result.success(stubDrivesListResponse.drives)
        viewModel = HomeViewModel(
            bannerUseCase,
            coverImageUseCase,
            pixivUseCase,
            officialNewsUseCase,
            agentsListUseCase,
            wEngineListUseCase,
            bangbooListUseCase,
            drivesListUseCase,
            settingsRepository,
        )
    }

    @Test
    fun `Init Data Success`() = runTest {
        val state = viewModel.uiState.value
        val agentsList = viewModel.agentsListState.value
        assertEquals(state.banner, stubBannerResponse)
        assertEquals(state.coverImage, stubCoverImageResponse)
        assertEquals(state.pixivTopics, stubPixivTopicResponse.getPopularArticles())
        assertEquals(state.newsList.first(), stubOfficialNewsState)
        assertEquals(agentsList, stubAgentsList)
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