/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.domain


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import app.wengine.data.FakeWEngineRepository
import app.wengine.model.stubWEngineDetailResponse
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WEngineDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val savedStateHandle = SavedStateHandle().apply {
        set("wEngineId", 47)
    }
    private val agentRepository = FakeWEngineRepository()
    private lateinit var viewModel: WEngineDetailViewModel

    @BeforeTest
    fun setup() {
        viewModel = WEngineDetailViewModel(savedStateHandle, agentRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.wEngineDetail, stubWEngineDetailResponse)
    }
}