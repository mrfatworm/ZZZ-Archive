/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.ktor.utils.io.core.toByteArray

@Entity
data class HoYoLabAccountEntity(
    @PrimaryKey(autoGenerate = false)
    val uid: Int,
    val region: String,
    val regionName: String,
    val lToken: ByteArray,
    val ltUid: ByteArray,
)

val stubHoYoLabAccountEntity = HoYoLabAccountEntity(
    uid = 123456789,
    region = "prod_gf_jp",
    regionName = "Asia",
    lToken = "fake_ltoken".toByteArray(),
    ltUid = "fake_lt_uid".toByteArray()
)
