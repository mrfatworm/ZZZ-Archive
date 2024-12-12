/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [HoYoLabAccountEntity::class], version = 1
)
@ConstructedBy(HoYoLabAccountDBConstructor::class)
@TypeConverters(ByteArrayConverter::class)
abstract class HoYoLabAccountDB : RoomDatabase() {
    abstract val hoYoLabAccountDao: HoYoLabAccountDao

    companion object {
        const val DATABASE_NAME = "hoyolab_account_list.db"
    }
}