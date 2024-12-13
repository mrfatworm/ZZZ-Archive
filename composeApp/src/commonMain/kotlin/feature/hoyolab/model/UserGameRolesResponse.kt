/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserGameRolesResponse(
    @SerialName("retcode")
    val retCode: Int,
    val message: String,
    val data: UserGameRolesData
)

@Serializable
data class UserGameRolesData(
    val list: List<UserGameRole>
)

@Serializable
data class UserGameRole(
    @SerialName("game_biz")
    val gameBiz: String,
    val region: String,
    @SerialName("game_uid")
    val gameUid: String,
    val nickname: String,
    val level: Int,
    @SerialName("is_chosen")
    val isChosen: Boolean,
    @SerialName("region_name")
    val regionName: String,
    @SerialName("is_official")
    val isOfficial: Boolean
)

val stubUserGameRolesResponse = UserGameRolesResponse(
    retCode = 0,
    message = "OK",
    data = UserGameRolesData(
        list = listOf(
            UserGameRole(
                gameBiz = "nap_global",
                region = "prod_gf_jp",
                gameUid = "1300051361",
                nickname = "海豚刑警",
                level = 56,
                isChosen = false,
                regionName = "Asia",
                isOfficial = true
            )
        )
    )
)

