/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import MainDispatcherRule
import database.UpdateDatabaseUseCase
import feature.banner.data.stubBannerResponse
import feature.banner.domain.BannerUseCase
import feature.cover.data.database.stubCoverImageListItemEntity
import feature.cover.domain.CoverImageUseCase
import feature.forum.domain.ForumUseCase
import feature.forum.model.stubAllForumState
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.domain.GameRecordUseCase
import feature.hoyolab.model.stubGameRecordResponse
import feature.hoyolab.model.stubSignResponse
import feature.news.domain.OfficialNewsUseCase
import feature.news.model.stubOfficialNewsListItem
import feature.pixiv.data.FakePixivRepository
import feature.pixiv.data.mapper.toPixivArticleList
import feature.pixiv.model.stubPixivTopicResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bannerUseCase = mockk<BannerUseCase>()
    private val coverImageUseCase = mockk<CoverImageUseCase>()
    private val pixivRepository = FakePixivRepository()
    private val officialNewsUseCase = mockk<OfficialNewsUseCase>()
    private val updateDatabaseUseCase = mockk<UpdateDatabaseUseCase>()
    private val gameRecordUseCase = mockk<GameRecordUseCase>()
    private val forumUseCase = mockk<ForumUseCase>()
    private lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        coEvery { updateDatabaseUseCase.updateCoverImages() } returns Unit
        coEvery { bannerUseCase.invoke() } returns Result.success(stubBannerResponse)
        coEvery { bannerUseCase.setBannerIgnoreId(any()) } returns Unit
        coEvery { coverImageUseCase.invoke() } returns flowOf(listOf(stubCoverImageListItemEntity))
        coEvery {
            officialNewsUseCase.getNewsListPeriodically(any(), any())
        } returns flowOf(Result.success(listOf(stubOfficialNewsListItem)))
        coEvery { gameRecordUseCase.getDefaultUid() } returns flowOf(1300051361)
        coEvery { gameRecordUseCase.getDefaultHoYoLabAccount(any()) } returns
            flowOf(
                stubHoYoLabAccountEntity
            )
        coEvery { gameRecordUseCase.getGameRecordPeriodically(any()) } returns
            flowOf(
                Result.success(stubGameRecordResponse.data)
            )
        coEvery { gameRecordUseCase.sign() } returns Result.success(stubSignResponse)
        coEvery { forumUseCase.getAllForumListPeriodically(any()) } returns
            flowOf(
                stubAllForumState
            )
        viewModel =
            HomeViewModel(
                bannerUseCase = bannerUseCase,
                coverImageUseCase = coverImageUseCase,
                pixivRepository = pixivRepository,
                newsUseCase = officialNewsUseCase,
                forumUseCase = forumUseCase,
                updateDatabaseUseCase = updateDatabaseUseCase,
                gameRecordUseCase = gameRecordUseCase
            )
    }

    @Test
    fun `Init data success`() = runTest {
        val state = viewModel.uiState.first()
        assertEquals(stubBannerResponse, state.banner)
        assertEquals(listOf(stubCoverImageListItemEntity), state.coverImage)
        assertEquals(stubPixivTopicResponse.toPixivArticleList(), state.pixivTopics)
        assertEquals(stubOfficialNewsListItem, state.newsList.first())
        assertEquals(stubAllForumState, state.allForum)
        coVerify { updateDatabaseUseCase.updateCoverImages() }
        coVerify { gameRecordUseCase.getDefaultUid() }
        coVerify { gameRecordUseCase.getDefaultHoYoLabAccount(any()) }
        coVerify { gameRecordUseCase.getGameRecordPeriodically(any()) }
    }

    @Test
    fun `Set banner ignore id as one THEN ignore banner data`() = runTest {
        viewModel.onAction(HomeAction.DismissBanner(1))
        coVerify { bannerUseCase.setBannerIgnoreId(1) }
    }

    @Test
    fun `GIVEN Account is empty WHEN Update HoYoLab card THEN reset card`() = runTest {
        coEvery { gameRecordUseCase.getDefaultHoYoLabAccount(any()) } returns flowOf(null)
        val state = viewModel.uiState.first()
        assertEquals(emptyGameRecordState, state.gameRecord)
    }

    @Test
    fun `Sign success`() = runTest {
        viewModel.uiState.first()
        viewModel.onAction(HomeAction.Sign)
        coVerify { gameRecordUseCase.sign() }
    }
}
