/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfficialActivities(
    @SerialName("retcode")
    val retCode: Int,
    val message: String,
    val data: Activities
)

@Serializable
data class Activities(
    val iTotal: Int,
    val list: List<Activity>
)

@Serializable
data class Activity(
    val sChanId: List<String>,
    val sTitle: String,
    val sIntro: String,
    val sUrl: String,
    val sAuthor: String,
    val sContent: String,
)
