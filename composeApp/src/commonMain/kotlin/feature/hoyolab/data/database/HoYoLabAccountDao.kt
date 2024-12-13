/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HoYoLabAccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: HoYoLabAccountEntity)

    @Query("SELECT * FROM HoYoLabAccountEntity")
    fun getAccountList(): Flow<List<HoYoLabAccountEntity>>

    @Query("SELECT * FROM HoYoLabAccountEntity WHERE uid = :uid")
    fun getAccount(uid: Int): Flow<HoYoLabAccountEntity>

    @Query("DELETE FROM HoYoLabAccountEntity WHERE uid = :uid")
    suspend fun deleteAccount(uid: Int)

    @Query("DELETE FROM HoYoLabAccountEntity")
    suspend fun deleteAccountList()
}