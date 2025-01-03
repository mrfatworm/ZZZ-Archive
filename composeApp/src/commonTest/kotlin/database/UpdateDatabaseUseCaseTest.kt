/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import feature.agent.data.repository.FakeAgentRepository
import feature.bangboo.data.repository.FakeBangbooRepository
import feature.cover_image.data.FakeCoverImageRepository
import feature.drive.data.FakeDriveRepository
import feature.home.data.FakeAssetVersionRepository
import feature.setting.data.FakeSystemConfigRepository
import feature.setting.domain.FakeLanguageUseCase
import feature.wengine.data.repository.FakeWEngineRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UpdateDatabaseUseCaseTest {
    private val assetVersionRepository = FakeAssetVersionRepository()
    private val coverImageRepository = FakeCoverImageRepository()
    private val agentRepository = FakeAgentRepository()
    private val wEngineRepository = FakeWEngineRepository()
    private val bangbooRepository = FakeBangbooRepository()
    private val driveRepository = FakeDriveRepository()
    private val systemConfigRepository = FakeSystemConfigRepository()
    private val languageUseCase = FakeLanguageUseCase()

    private val updateDatabaseUseCase = UpdateDatabaseUseCase(
        assetVersionRepository,
        coverImageRepository,
        agentRepository,
        wEngineRepository,
        bangbooRepository,
        driveRepository,
        systemConfigRepository,
        languageUseCase
    )

    @Test
    fun `Update assets if new version available`() = runTest {
        updateDatabaseUseCase.updateAssetsIfNewVersionAvailable()
        assertEquals(2, systemConfigRepository.getCoverImageDBVersion().first())
        assertEquals(2, systemConfigRepository.getAgentListDBVersion().first())
        assertEquals(2, systemConfigRepository.getWEngineListDBVersion().first())
        assertEquals(2, systemConfigRepository.getBangbooListDBVersion().first())
        assertEquals(2, systemConfigRepository.getDriveListDBVersion().first())
    }

    @Test
    fun `Reset wiki database version`() = runTest {
        updateDatabaseUseCase.resetWikiDatabaseVersion()
        assertEquals(0, systemConfigRepository.getAgentListDBVersion().first())
        assertEquals(0, systemConfigRepository.getWEngineListDBVersion().first())
        assertEquals(0, systemConfigRepository.getBangbooListDBVersion().first())
        assertEquals(0, systemConfigRepository.getDriveListDBVersion().first())
    }
}