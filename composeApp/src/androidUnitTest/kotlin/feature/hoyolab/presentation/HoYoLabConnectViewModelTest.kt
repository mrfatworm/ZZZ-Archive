/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation


import MainDispatcherRule
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.domain.HoYoLabManageUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HoYoLabConnectViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val hoYoLabManageUseCase = mockk<HoYoLabManageUseCase>()
    private lateinit var viewModel: HoYoLabConnectViewModel

    @BeforeTest
    fun setup() {
        coEvery {
            hoYoLabManageUseCase.requestUserGameRolesAndSave(any(), any(), any())
        } returns Result.success(Unit)
        coEvery { hoYoLabManageUseCase.getAllAccountsFromDB() } returns flowOf(
            listOf(
                stubHoYoLabAccountEntity
            )
        )
        coEvery { hoYoLabManageUseCase.deleteAccountFromDB(any()) } returns Unit
        viewModel = HoYoLabConnectViewModel(hoYoLabManageUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(state.connectedAccounts.first().uid, stubHoYoLabAccountEntity.uid.toString())
    }

    @Test
    fun `Add account`() {
        viewModel.onAction(HoYoLabConnectAction.ConnectToHoYoLabAndAdd("", "", ""))
        coVerify { hoYoLabManageUseCase.requestUserGameRolesAndSave("", "", "") }
    }
}