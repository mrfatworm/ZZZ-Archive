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