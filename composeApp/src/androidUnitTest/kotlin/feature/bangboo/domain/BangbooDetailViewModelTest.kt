/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.domain


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import feature.bangboo.model.stubBangbooDetailResponse
import feature.bangboo.presentation.BangbooDetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BangbooDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val savedStateHandle = SavedStateHandle().apply {
        set("bangbooId", 6)
    }
    private val bangbooDetailUseCase = mockk<BangbooDetailUseCase>()
    private lateinit var viewModel: BangbooDetailViewModel

    @BeforeTest
    fun setup() {
        coEvery { bangbooDetailUseCase.invoke(any()) } returns Result.success(
            stubBangbooDetailResponse
        )
        viewModel = BangbooDetailViewModel(savedStateHandle, bangbooDetailUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.bangbooDetail, stubBangbooDetailResponse)
    }
}