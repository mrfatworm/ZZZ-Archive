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
import feature.hoyolab.model.agent.ShareMessage
import feature.hoyolab.model.agent.stubMyAgentDetail
import feature.setting.data.FakePreferenceRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import utils.share.FakeImageShareHandler
import utils.share.ShareOutcome

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
    private val imageShareHandler = FakeImageShareHandler()
    private val viewModel = buildViewModel(imageShareHandler)

    private fun buildViewModel(shareHandler: FakeImageShareHandler) = MyAgentDetailViewModel(
        savedStateHandle = savedStateHandle,
        hoYoLabAgentUseCase = hoYoLabAgentUseCase,
        hoYoLabPreferenceUseCase = HoYoLabPreferenceUseCase(preferencesRepository),
        imageShareHandler = shareHandler
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

    @Test
    fun `Whether the card can be saved follows the platform`() {
        assertTrue(viewModel.uiState.value.canSaveImage)
        assertEquals(false, buildViewModel(FakeImageShareHandler(canSave = false)).uiState.value.canSaveImage)
    }

    @Test
    fun `Share options toggle`() {
        viewModel.onAction(MyAgentDetailAction.SetShareDriveDetails(false))
        viewModel.onAction(MyAgentDetailAction.SetShareDarkTheme(false))
        viewModel.onAction(MyAgentDetailAction.SetShowUid(true))
        val state = viewModel.uiState.value
        assertEquals(false, state.shareOptions.showDriveDetails)
        assertEquals(false, state.shareOptions.isDarkTheme)
        assertEquals(true, state.showUid)
    }

    @Test
    fun `Sharing hands the PNG to the platform under a dated file name`() {
        val png = byteArrayOf(1, 2, 3)
        viewModel.onAction(MyAgentDetailAction.ShareCard(png))
        assertContentEquals(png, imageShareHandler.lastSharedPng)
        val fileName = imageShareHandler.lastFileName!!
        assertTrue(fileName.startsWith("zzz-archive-agent-${stubMyAgentDetail.id}-"), fileName)
        assertTrue(fileName.endsWith(".png"), fileName)
        // The share sheet gives its own feedback, so the state carries none.
        assertNull(viewModel.uiState.value.shareMessage)
    }

    @Test
    fun `A clipboard copy and a save are confirmed`() {
        val copying = FakeImageShareHandler(shareOutcome = ShareOutcome.Copied)
        val copyViewModel = buildViewModel(copying)
        copyViewModel.onAction(MyAgentDetailAction.ShareCard(byteArrayOf(1)))
        assertEquals(ShareMessage.Copied, copyViewModel.uiState.value.shareMessage)

        viewModel.onAction(MyAgentDetailAction.SaveCard(byteArrayOf(2)))
        assertContentEquals(byteArrayOf(2), imageShareHandler.lastSavedPng)
        assertEquals(ShareMessage.Saved, viewModel.uiState.value.shareMessage)
    }

    @Test
    fun `A cancelled save says nothing`() {
        val cancelling = FakeImageShareHandler(saveOutcome = ShareOutcome.Cancelled)
        val cancelViewModel = buildViewModel(cancelling)
        cancelViewModel.onAction(MyAgentDetailAction.SaveCard(byteArrayOf(1)))
        assertNull(cancelViewModel.uiState.value.shareMessage)
    }

    @Test
    fun `A failed share is reported and can be dismissed`() {
        imageShareHandler.failure = IllegalStateException("no activity")
        viewModel.onAction(MyAgentDetailAction.ShareCard(byteArrayOf(1)))
        assertEquals(ShareMessage.Failed, viewModel.uiState.value.shareMessage)
        viewModel.onAction(MyAgentDetailAction.DismissShareMessage)
        assertNull(viewModel.uiState.value.shareMessage)
    }
}
