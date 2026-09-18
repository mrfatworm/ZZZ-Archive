/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import MainDispatcherTest
import androidx.lifecycle.SavedStateHandle
import feature.hoyolab.data.credential.FakeHoYoLabCredentialStore
import feature.hoyolab.data.database.FakeHoYoLabAccountDao
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabAgentRepository
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.domain.HoYoLabPreferenceUseCase
import feature.hoyolab.model.agent.stubMyAgentDetail
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlin.test.Test
import kotlin.test.assertEquals

class MyAgentDetailViewModelTest : MainDispatcherTest() {
    private val preferencesRepository = FakePreferenceRepository(stubHoYoLabAccountEntity.uid)
    private val hoYoLabAgentUseCase =
        HoYoLabAgentUseCase(
            repository = FakeHoYoLabAgentRepository(),
            accountDao = FakeHoYoLabAccountDao(),
            preferencesRepository = preferencesRepository,
            credentialStore = FakeHoYoLabCredentialStore(),
            languageUseCase = FakeLanguageUseCase()
        )
    private val savedStateHandle = SavedStateHandle().apply { set("id", 1) }
    private val viewModel =
        MyAgentDetailViewModel(
            savedStateHandle = savedStateHandle,
            hoYoLabAgentUseCase = hoYoLabAgentUseCase,
            hoYoLabPreferenceUseCase = HoYoLabPreferenceUseCase(preferencesRepository)
        )

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(stubMyAgentDetail, state.agentDetail)
        assertEquals(stubHoYoLabAccountEntity.uid.toString(), state.uid)
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
        assertEquals(true, state.showUid)
        assertEquals(true, state.isCustomImage)
        assertEquals("customUrl", state.customImgUrl)
        assertEquals("customAuthor", state.customImgAuthor)
        assertEquals(true, state.hasBlurBackground)
        assertEquals(true, state.adjustMode)
    }

    @Test
    fun `Default outfit is the original one`() {
        val state = viewModel.uiState.value
        assertEquals(2, state.agentDetail.skins.size)
        assertEquals(
            stubMyAgentDetail.skins.first { it.isOriginal }.id,
            state.selectedSkinId
        )
    }

    @Test
    fun `Select outfit`() {
        val alternative = stubMyAgentDetail.skins.first { !it.isOriginal }
        viewModel.onAction(MyAgentDetailAction.SelectSkin(alternative.id))
        assertEquals(alternative.id, viewModel.uiState.value.selectedSkinId)
    }

    @Test
    fun `Adjust image done`() {
        viewModel.onAction(MyAgentDetailAction.AdjustImageDone)
        val state = viewModel.uiState.value
        assertEquals(false, state.adjustMode)
    }
}
