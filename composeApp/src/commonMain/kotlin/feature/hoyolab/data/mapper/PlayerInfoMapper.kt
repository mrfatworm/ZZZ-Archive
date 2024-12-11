/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.mapper

import feature.hoyolab.model.PlayerAccountInfo
import feature.hoyolab.model.UserGameRole

fun UserGameRole.toPlayerAccountInfo(): PlayerAccountInfo {
    return PlayerAccountInfo(
        region = region,
        regionName = regionName,
        uid = gameUid,
        nickname = nickname,
        level = level
    )
}