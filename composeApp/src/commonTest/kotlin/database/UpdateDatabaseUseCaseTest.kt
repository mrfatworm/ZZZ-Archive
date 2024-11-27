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
import feature.wengine.data.repository.FakeWEngineRepository
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

    private val updateDatabaseUseCase = UpdateDatabaseUseCase(
        assetVersionRepository,
        coverImageRepository,
        agentRepository,
        wEngineRepository,
        bangbooRepository,
        driveRepository,
        systemConfigRepository
    )

    @Test
    fun `Update assets if new version available`() = runTest {
        updateDatabaseUseCase.updateAssetsIfNewVersionAvailable()
        assertEquals(systemConfigRepository.getCoverImageDBVersion(), 2)
        assertEquals(systemConfigRepository.getAgentListDBVersion(), 2)
        assertEquals(systemConfigRepository.getWEngineListDBVersion(), 2)
        assertEquals(systemConfigRepository.getBangbooListDBVersion(), 2)
        assertEquals(systemConfigRepository.getDriveListDBVersion(), 2)
    }

    @Test
    fun `Reset wiki database version`() = runTest {
        updateDatabaseUseCase.resetWikiDatabaseVersion()
        assertEquals(systemConfigRepository.getAgentListDBVersion(), 0)
        assertEquals(systemConfigRepository.getWEngineListDBVersion(), 0)
        assertEquals(systemConfigRepository.getBangbooListDBVersion(), 0)
        assertEquals(systemConfigRepository.getDriveListDBVersion(), 0)
    }
}