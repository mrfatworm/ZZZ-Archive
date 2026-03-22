/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.domain.HoYoLabPreferenceUseCase
import feature.hoyolab.model.agent.stubMyAgentDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule

class MyAgentDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val hoYoLabAgentUseCase = mockk<HoYoLabAgentUseCase>()
    private val hoYoLabPreferenceUseCase = mockk<HoYoLabPreferenceUseCase>()
    private val savedStateHandle =
        SavedStateHandle().apply {
            set("id", 1)
        }
    private lateinit var viewModel: MyAgentDetailViewModel

    @BeforeTest
    fun setup() {
        coEvery {
            hoYoLabAgentUseCase.getAgentDetail(any())
        } returns Result.success(stubMyAgentDetail)
        coEvery {
            hoYoLabPreferenceUseCase.getDefaultHoYoLabAccountUid()
        } returns flowOf(1300051361)

        viewModel =
            MyAgentDetailViewModel(
                savedStateHandle = savedStateHandle,
                hoYoLabAgentUseCase = hoYoLabAgentUseCase,
                hoYoLabPreferenceUseCase = hoYoLabPreferenceUseCase
            )
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(stubMyAgentDetail, state.agentDetail)
        assertEquals("1300051361", state.uid)
    }

    @Test
    fun `Apply custom image`() {
        viewModel.onAction(
            MyAgentDetailAction.ConfirmEditImage(
                true,
                true,
                "customUrl",
                "customAuthor",
                true
            )
        )
        val state = viewModel.uiState.value
        assertEquals(state.showUid, true)
        assertEquals(state.isCustomImage, true)
        assertEquals(state.customImgUrl, "customUrl")
        assertEquals(state.customImgAuthor, "customAuthor")
        assertEquals(state.hasBlurBackground, true)
        assertEquals(state.adjustMode, true)
    }

    @Test
    fun `Adjust image done`() {
        viewModel.onAction(MyAgentDetailAction.AdjustImageDone)
        val state = viewModel.uiState.value
        assertEquals(state.adjustMode, false)
    }
}
