/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.domain


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import app.bangboo.data.FakeBangbooRepository
import app.bangboo.model.stubBangbooDetailResponse
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
    private val bangbooRepository = FakeBangbooRepository()
    private lateinit var viewModel: BangbooDetailViewModel

    @BeforeTest
    fun setup() {
        viewModel = BangbooDetailViewModel(savedStateHandle, bangbooRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.bangbooDetail, stubBangbooDetailResponse)
    }
}