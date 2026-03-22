/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import MainDispatcherRule
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.domain.HoYoLabManageUseCase
import feature.hoyolab.domain.HoYoLabPreferenceUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule

class HoYoLabSyncViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val hoYoLabManageUseCase = mockk<HoYoLabManageUseCase>()
    private val hoYoLabPreferenceUseCase = mockk<HoYoLabPreferenceUseCase>()
    private lateinit var viewModel: HoYoLabSyncViewModel

    @BeforeTest
    fun setup() {
        coEvery {
            hoYoLabManageUseCase.requestUserInfoAndSave(any(), any(), any())
        } returns Result.success(Unit)
        coEvery { hoYoLabManageUseCase.getAllAccountsFromDB() } returns
            flowOf(
                listOf(
                    stubHoYoLabAccountEntity
                )
            )
        coEvery { hoYoLabManageUseCase.reSyncAccount(any()) } returns Unit
        coEvery { hoYoLabManageUseCase.deleteAccountFromDB(any()) } returns Unit
        coEvery { hoYoLabPreferenceUseCase.getDefaultHoYoLabAccountUid() } returns flowOf(123456789)
        coEvery { hoYoLabPreferenceUseCase.setDefaultHoYoLabAccountUid(any()) } returns Unit
        every { hoYoLabManageUseCase.convertToLocalDatetime(any()) } returns "2024-12-12 14:56"
        viewModel = HoYoLabSyncViewModel(hoYoLabManageUseCase, hoYoLabPreferenceUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(stubHoYoLabAccountEntity.uid.toString(), state.syncedAccounts.first().uid)
        assertEquals(state.syncedAccounts.first().datetime, "2024-12-12 14:56")
        assertEquals(state.defaultAccountUid, "123456789")
    }

    @Test
    fun `Add account`() {
        viewModel.onAction(HoYoLabSyncAction.ConnectToHoYoLabAndAdd(region = "", lToken = "", ltUid = ""))
        coVerify { hoYoLabManageUseCase.requestUserInfoAndSave(region = "", lToken = "", ltUid = "") }
    }

    @Test
    fun `Re-sync account`() {
        viewModel.onAction(HoYoLabSyncAction.SyncAccount("123456789"))
        coVerify { hoYoLabManageUseCase.reSyncAccount(123456789) }
    }

    @Test
    fun `Delete account`() {
        viewModel.onAction(HoYoLabSyncAction.DeleteAccount("123456789"))
        coVerify { hoYoLabManageUseCase.deleteAccountFromDB(123456789) }
    }

    @Test
    fun `Set account as default`() {
        viewModel.onAction(HoYoLabSyncAction.SetDefaultAccount("123456789"))
        coVerify { hoYoLabPreferenceUseCase.setDefaultHoYoLabAccountUid(123456789) }
    }
}
