/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import MainDispatcherTest
import database.UpdateDatabaseUseCase
import feature.agent.data.repository.FakeAgentRepository
import feature.banner.data.FakeBannerRepository
import feature.banner.data.stubBannerResponse
import feature.banner.domain.BannerUseCase
import feature.cover.data.FakeCoverImageRepository
import feature.cover.data.database.stubCoverImageListItemEntity
import feature.cover.domain.CoverImageUseCase
import feature.forum.data.FakeForumRepository
import feature.forum.domain.ForumUseCase
import feature.forum.model.stubAllForumState
import feature.hoyolab.data.crypto.FakeZzzCrypto
import feature.hoyolab.data.database.FakeHoYoLabAccountDao
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabConfigRepository
import feature.hoyolab.domain.GameRecordUseCase
import feature.hoyolab.model.stubSignResponse
import feature.news.data.FakeOfficialNewsRepository
import feature.news.data.mapper.toOfficialNewsList
import feature.news.domain.OfficialNewsUseCase
import feature.news.model.stubOfficialNewsDataResponseResponse
import feature.pixiv.data.FakePixivRepository
import feature.pixiv.data.mapper.toPixivArticleList
import feature.pixiv.model.stubPixivTopicResponse
import feature.setting.data.FakePreferenceRepository
import feature.setting.data.FakeSystemConfigRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import utils.Language

class HomeViewModelTest : MainDispatcherTest() {
    private val systemConfigRepository = FakeSystemConfigRepository()
    private val coverImageRepository = FakeCoverImageRepository()

    private fun createViewModel(defaultAccountUid: Int = stubHoYoLabAccountEntity.uid): HomeViewModel {
        val preferencesRepository = FakePreferenceRepository(defaultAccountUid)
        return HomeViewModel(
            bannerUseCase =
                BannerUseCase(
                    FakeBannerRepository(),
                    systemConfigRepository,
                    FakeLanguageUseCase()
                ),
            coverImageUseCase = CoverImageUseCase(coverImageRepository),
            pixivRepository = FakePixivRepository(),
            newsUseCase = OfficialNewsUseCase(FakeOfficialNewsRepository(), FakeLanguageUseCase()),
            forumUseCase = ForumUseCase(FakeForumRepository()),
            updateDatabaseUseCase =
                UpdateDatabaseUseCase(coverImageRepository, FakeAgentRepository()),
            gameRecordUseCase =
                GameRecordUseCase(
                    hoYoLabConfigRepository = FakeHoYoLabConfigRepository(),
                    accountDao = FakeHoYoLabAccountDao(),
                    preferencesRepository = preferencesRepository,
                    zzzCrypto = FakeZzzCrypto(),
                    languageUseCase = FakeLanguageUseCase()
                )
        )
    }

    @Test
    fun `Init data success`() = runViewModelTest {
        val state = createViewModel().uiState.first()
        assertEquals(stubBannerResponse, state.banner)
        assertEquals(listOf(stubCoverImageListItemEntity), state.coverImage)
        assertEquals(stubPixivTopicResponse.toPixivArticleList(), state.pixivTopics)
        assertEquals(
            stubOfficialNewsDataResponseResponse.toOfficialNewsList(Language.English.officialCode),
            state.newsList
        )
        assertEquals(stubAllForumState, state.allForum)
        assertEquals(1, coverImageRepository.requestAndUpdateCoverImagesListDBCount)
        assertTrue(state.gameRecord.hasAccount)
        assertEquals(stubHoYoLabAccountEntity.uid.toString(), state.gameRecord.uid)
        assertEquals(stubHoYoLabAccountEntity.nickName, state.gameRecord.nickname)
    }

    @Test
    fun `Dismiss banner THEN remember the ignored id`() = runViewModelTest {
        val viewModel = createViewModel()
        viewModel.uiState.first()
        viewModel.onAction(HomeAction.DismissBanner(stubBannerResponse.id))
        assertEquals(stubBannerResponse.id, systemConfigRepository.getBannerIgnoreId().first())
        assertNull(viewModel.uiState.value.banner)
    }

    @Test
    fun `GIVEN Account is empty WHEN Update HoYoLab card THEN reset card`() = runViewModelTest {
        val state = createViewModel(defaultAccountUid = 0).uiState.first()
        assertEquals(emptyGameRecordState, state.gameRecord)
    }

    @Test
    fun `Sign success`() = runViewModelTest {
        val viewModel = createViewModel()
        viewModel.uiState.first()
        viewModel.onAction(HomeAction.Sign)
        assertEquals(stubSignResponse.message, viewModel.uiState.value.signResult)
    }
}
