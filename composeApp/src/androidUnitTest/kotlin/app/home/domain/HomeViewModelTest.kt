/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.domain


import MainDispatcherRule
import app.agent.data.AgentRepository
import app.agent.model.stubAgentsListResponse
import app.home.data.BannerRepository
import app.home.data.FakeAgentRepository
import app.home.data.FakeBannerRepository
import app.home.data.FakeNewsRepository
import app.home.data.FakePixivRepository
import app.home.data.NewsRepository
import app.home.data.PixivRepository
import app.home.model.stubBannerResponse
import app.home.model.stubOfficialNewsDataResponse
import app.home.model.stubPixivZzzTopic
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var bannerRepository: BannerRepository
    private lateinit var pixivRepository: PixivRepository
    private lateinit var newsRepository: NewsRepository
    private lateinit var agentRepository: AgentRepository
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        bannerRepository = FakeBannerRepository()
        pixivRepository = FakePixivRepository()
        newsRepository = FakeNewsRepository()
        agentRepository = FakeAgentRepository()
        viewModel =
            HomeViewModel(bannerRepository, pixivRepository, newsRepository, agentRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertThat(state.banner).isEqualTo(stubBannerResponse)
        assertThat(state.pixivPuppiesList).isEqualTo(stubPixivZzzTopic.getPopularArticles())
        assertThat(state.news).isEqualTo(stubOfficialNewsDataResponse)
        assertThat(state.agentsList).isEqualTo(stubAgentsListResponse.agents)
    }
}