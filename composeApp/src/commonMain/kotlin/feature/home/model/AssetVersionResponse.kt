/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetVersionResponse(
    @SerialName("agents_list")
    val agentsList: Int,
    @SerialName("w_engines_list")
    val wEnginesList: Int,
    @SerialName("bangboo_list")
    val bangbooList: Int,
    @SerialName("drives_list")
    val drivesList: Int,
    @SerialName("cover_images_list")
    val coverImagesList: Int
)

val stubAssetVersionResponse = AssetVersionResponse(
    agentsList = 2,
    wEnginesList = 2,
    bangbooList = 2,
    drivesList = 2,
    coverImagesList = 2
)
