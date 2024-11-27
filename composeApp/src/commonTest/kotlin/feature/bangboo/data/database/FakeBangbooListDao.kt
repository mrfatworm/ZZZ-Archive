/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBangbooListDao : BangbooListDao {
    private val bangbooList = mutableListOf(stubBangbooListItemEntity)

    override suspend fun setBangbooList(bangbooList: List<BangbooListItemEntity>) {
        this.bangbooList.clear()
        this.bangbooList.addAll(bangbooList)
    }

    override fun getBangbooList(): Flow<List<BangbooListItemEntity>> = flow {
        emit(bangbooList)
    }

    override suspend fun deleteBangbooList() {
        this.bangbooList.clear()
    }
}