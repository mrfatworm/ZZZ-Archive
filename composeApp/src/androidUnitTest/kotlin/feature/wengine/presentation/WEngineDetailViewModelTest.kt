/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import feature.wengine.domain.WEngineDetailUseCase
import feature.wengine.model.stubWEngineDetail
import io.mockk.coEvery
import io.mockk.mockk
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
    private val wEngineDetailUseCase = mockk<WEngineDetailUseCase>()
    private lateinit var viewModel: WEngineDetailViewModel

    @BeforeTest
    fun setup() {
        coEvery { wEngineDetailUseCase.invoke(any()) } returns Result.success(stubWEngineDetail)
        viewModel = WEngineDetailViewModel(savedStateHandle, wEngineDetailUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(state.wEngineDetail, stubWEngineDetail)
    }
}