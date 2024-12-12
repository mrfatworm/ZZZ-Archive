/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HoYoLabAccountEntity(
    @PrimaryKey(autoGenerate = false)
    val uid: Int,
    val region: String,
    val regionName: String,
    val lToken: ByteArray,
    val ltUid: ByteArray,
)
