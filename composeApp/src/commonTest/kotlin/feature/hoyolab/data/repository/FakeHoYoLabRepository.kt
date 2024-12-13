/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.model.PlayerAccountInfo
import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.stubPlayerAccountInfo
import feature.hoyolab.model.stubPlayerDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHoYoLabRepository : HoYoLabRepository {
    private var isError = false
    private var isEmptyAccountList = false
    var isAdded = false
        private set
    var isDeleted = false
        private set

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    fun setIsEmptyAccountList(isEmptyAccountList: Boolean) {
        this.isEmptyAccountList = isEmptyAccountList
    }

    override suspend fun requestUserGameRolesByLToken(
        region: String,
        lToken: String,
        ltUid: String
    ): Result<List<PlayerAccountInfo>> {
        return if (isError) {
            Result.failure(Exception())
        } else if (isEmptyAccountList) {
            Result.success(emptyList())
        } else {
            Result.success(listOf(stubPlayerAccountInfo))
        }
    }

    override suspend fun requestPlayerDetail(
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String
    ): Result<PlayerDetailResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubPlayerDetailResponse)
        }
    }

    override suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>> = flow {
        emit(listOf(stubHoYoLabAccountEntity))
    }

    override suspend fun getAccountFromDB(uid: Int): Flow<HoYoLabAccountEntity> = flow {
        emit(stubHoYoLabAccountEntity)
    }

    override suspend fun addAccountToDB(
        uid: Int,
        region: String,
        regionName: String,
        lToken: ByteArray,
        ltUid: ByteArray
    ) {
        isAdded = true
    }

    override suspend fun deleteAccountFromDB(uid: Int) {
        isDeleted = true
    }
}