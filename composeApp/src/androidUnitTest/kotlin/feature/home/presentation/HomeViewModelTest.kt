/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation


import MainDispatcherRule
import database.UpdateDatabaseUseCase
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsList
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.stubBangbooList
import feature.banner.data.stubBannerResponse
import feature.banner.domain.BannerUseCase
import feature.cover_image.data.database.stubCoverImageListItemEntity
import feature.cover_image.domain.CoverImageUseCase
import feature.drive.data.database.stubDrivesListItemEntity
import feature.drive.domain.DrivesListUseCase
import feature.news.data.stubOfficialNewsDataResponse
import feature.news.domain.OfficialNewsUseCase
import feature.news.model.stubOfficialNewsState
import feature.pixiv.data.stubPixivTopicResponse
import feature.pixiv.domain.PixivUseCase
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
    private val wEnginesListUseCase = mockk<WEnginesListUseCase>()
    private val bangbooListUseCase = mockk<BangbooListUseCase>()
    private val drivesListUseCase = mockk<DrivesListUseCase>()
    private val updateDatabaseUseCase = mockk<UpdateDatabaseUseCase>()
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        coEvery { updateDatabaseUseCase.updateAssetsIfNewVersionAvailable() } returns Unit
        coEvery { bannerUseCase.invoke() } returns Result.success(stubBannerResponse)
        every { bannerUseCase.getBannerIgnoreId() } returns 0
        every { bannerUseCase.setBannerIgnoreId(any()) } returns Unit
        coEvery { coverImageUseCase.invoke() } returns flowOf(listOf(stubCoverImageListItemEntity))
        coEvery { pixivUseCase.invoke(any()) } returns Result.success(stubPixivTopicResponse.getPopularArticles())
        coEvery {
            officialNewsUseCase.getNewsPeriodically(any(), any())
        } returns flowOf(Result.success(stubOfficialNewsDataResponse.data.list))
        every { officialNewsUseCase.convertToOfficialNewsState(any()) } returns listOf(
            stubOfficialNewsState
        )
        coEvery { agentsListUseCase.invoke() } returns flowOf(stubAgentsList)
        coEvery { wEnginesListUseCase.invoke() } returns flowOf(stubWEnginesList)
        coEvery { bangbooListUseCase.invoke() } returns flowOf(stubBangbooList)
        coEvery { drivesListUseCase.invoke() } returns flowOf(listOf(stubDrivesListItemEntity))
        viewModel = HomeViewModel(
            bannerUseCase,
            coverImageUseCase,
            pixivUseCase,
            officialNewsUseCase,
            agentsListUseCase,
            wEnginesListUseCase,
            bangbooListUseCase,
            drivesListUseCase,
            updateDatabaseUseCase
        )
    }

    @Test
    fun `Init Data Success`() = runTest {
        val state = viewModel.uiState.value
        assertEquals(state.banner, stubBannerResponse)
        assertEquals(state.coverImage, listOf(stubCoverImageListItemEntity))
        assertEquals(state.pixivTopics, stubPixivTopicResponse.getPopularArticles())
        assertEquals(state.newsList.first(), stubOfficialNewsState)
        assertEquals(state.agentsList, stubAgentsList)
        assertEquals(state.wEnginesList, stubWEnginesList)
        assertEquals(state.bangbooList, stubBangbooList)
        assertEquals(state.drivesList, listOf(stubDrivesListItemEntity))
        coVerify { updateDatabaseUseCase.updateAssetsIfNewVersionAvailable() }
    }

    @Test
    fun `Set Banner Ignore Id as One than ignore banner data`() {
        viewModel.closeBannerAndIgnoreId(1)
        verify { bannerUseCase.setBannerIgnoreId(1) }
    }
}