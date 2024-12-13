/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHoYoLabAccountDao : HoYoLabAccountDao {
    private val accountList = mutableListOf(stubHoYoLabAccountEntity)

    override suspend fun insertAccount(account: HoYoLabAccountEntity) {
        accountList.add(account)
    }

    override fun getAccountList(): Flow<List<HoYoLabAccountEntity>> = flow {
        emit(accountList)
    }

    override fun getAccount(uid: Int): Flow<HoYoLabAccountEntity> = flow {
        accountList.forEach {
            if (it.uid == uid) {
                emit(it)
            }
        }
    }

    override suspend fun deleteAccount(uid: Int) {
        accountList.removeAt(accountList.indexOfFirst { it.uid == uid })
    }

    override suspend fun deleteAccountList() {
        accountList.clear()
    }
}