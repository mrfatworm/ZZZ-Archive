/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import MainDispatcherTest
import feature.hoyolab.data.credential.FakeHoYoLabCredentialStore
import feature.hoyolab.data.database.FakeHoYoLabAccountDao
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabAgentRepository
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.model.stubMyAgentsList
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlin.test.Test
import kotlin.test.assertEquals

class MyAgentsListViewModelTest : MainDispatcherTest() {
    private val agentRepository = FakeHoYoLabAgentRepository()
    private val hoYoLabAgentUseCase =
        HoYoLabAgentUseCase(
            repository = agentRepository,
            accountDao = FakeHoYoLabAccountDao(),
            preferencesRepository = FakePreferenceRepository(stubHoYoLabAccountEntity.uid),
            credentialStore = FakeHoYoLabCredentialStore(),
            languageUseCase = FakeLanguageUseCase()
        )
    private val viewModel = MyAgentsListViewModel(hoYoLabAgentUseCase)

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(stubMyAgentsList, state.agentsList)
    }
}
