/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import feature.bangboo.domain.BangbooDetailUseCase
import feature.bangboo.model.stubBangbooDetail
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
            stubBangbooDetail
        )
        viewModel = BangbooDetailViewModel(savedStateHandle, bangbooDetailUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(stubBangbooDetail, state.bangbooDetail)
    }
}