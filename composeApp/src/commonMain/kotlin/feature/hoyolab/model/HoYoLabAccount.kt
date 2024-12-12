/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

data class HoYoLabAccount(
    val uid: Int,
    val region: String,
    val regionName: String,
    val lToken: String,
    val ltUid: String,
)
