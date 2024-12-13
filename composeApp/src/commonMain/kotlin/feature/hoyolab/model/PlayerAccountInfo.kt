/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model


data class PlayerAccountInfo(
    val region: String,
    val regionName: String,
    val uid: String,
    val nickname: String,
    val level: Int,
)

val stubPlayerAccountInfo = PlayerAccountInfo(
    region = "prod_gf_jp",
    regionName = "Asia",
    uid = "1300051361",
    nickname = "海豚刑警",
    level = 56
)