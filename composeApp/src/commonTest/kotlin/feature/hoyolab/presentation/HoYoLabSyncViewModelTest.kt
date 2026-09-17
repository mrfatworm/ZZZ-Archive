/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import MainDispatcherTest
import feature.hoyolab.data.crypto.FakeZzzCrypto
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabConfigRepository
import feature.hoyolab.domain.HoYoLabManageUseCase
import feature.hoyolab.domain.HoYoLabPreferenceUseCase
import feature.setting.data.FakePreferenceRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first

class HoYoLabSyncViewModelTest : MainDispatcherTest() {
    private val hoYoLabConfigRepository = FakeHoYoLabConfigRepository()
    private val preferencesRepository = FakePreferenceRepository(stubHoYoLabAccountEntity.uid)
    private val hoYoLabManageUseCase =
        HoYoLabManageUseCase(hoYoLabConfigRepository, FakeZzzCrypto(), preferencesRepository)
    private val viewModel =
        HoYoLabSyncViewModel(
            hoYoLabManageUseCase,
            HoYoLabPreferenceUseCase(preferencesRepository)
        )

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        val account = state.syncedAccounts.first()
        assertEquals(stubHoYoLabAccountEntity.uid.toString(), account.uid)
        assertEquals(stubHoYoLabAccountEntity.nickName, account.nickname)
        assertEquals(
            hoYoLabManageUseCase.convertToLocalDatetime(stubHoYoLabAccountEntity.updatedAt),
            account.datetime
        )
        assertEquals(stubHoYoLabAccountEntity.uid.toString(), state.defaultAccountUid)
    }

    @Test
    fun `Add account`() = runViewModelTest {
        viewModel.onAction(
            HoYoLabSyncAction.ConnectToHoYoLabAndAdd(region = "", lToken = "", ltUid = "")
        )
        assertEquals(2, hoYoLabConfigRepository.getAllAccountsFromDB().first().size)
        assertFalse(viewModel.uiState.value.openAddAccountDialog)
    }

    @Test
    fun `Add account fail THEN show error message`() = runViewModelTest {
        hoYoLabConfigRepository.setError(true)
        viewModel.onAction(
            HoYoLabSyncAction.ConnectToHoYoLabAndAdd(region = "", lToken = "", ltUid = "")
        )
        assertTrue(viewModel.uiState.value.errorMessage.isNotEmpty())
    }

    @Test
    fun `Re-sync account`() = runViewModelTest {
        viewModel.onAction(HoYoLabSyncAction.SyncAccount(stubHoYoLabAccountEntity.uid.toString()))
        assertEquals(2, hoYoLabConfigRepository.getAllAccountsFromDB().first().size)
        // Re-sync starts an 8 second cool down before the button becomes clickable again
        assertFalse(viewModel.uiState.value.syncable)
    }

    @Test
    fun `Delete account`() = runViewModelTest {
        viewModel.onAction(HoYoLabSyncAction.DeleteAccount(stubHoYoLabAccountEntity.uid.toString()))
        assertTrue(hoYoLabConfigRepository.getAllAccountsFromDB().first().isEmpty())
    }

    @Test
    fun `Set account as default`() = runViewModelTest {
        viewModel.onAction(HoYoLabSyncAction.SetDefaultAccount("1300051361"))
        assertEquals(1300051361, preferencesRepository.getDefaultHoYoLabAccountUid().first())
    }
}
