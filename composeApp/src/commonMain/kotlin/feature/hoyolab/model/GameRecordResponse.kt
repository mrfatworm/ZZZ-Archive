/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameRecordResponse(
    @SerialName("retcode") val retCode: Int, val message: String, val data: GameRecordData
)

@Serializable
data class GameRecordData(
    val energy: Energy,
    val vitality: Progress,
    @SerialName("vhs_sale") val vhsSale: VhsSale,
    @SerialName("card_sign") val cardSign: String,
    @SerialName("bounty_commission") val bountyCommission: BountyCommission?,
    @SerialName("survey_points") val surveyPoints: SurveyPoints?,
    @SerialName("abyss_refresh") val abyssRefresh: Long,
    val coffee: Coffee?,
    @SerialName("weekly_task") val weeklyTask: WeeklyTask?
)

@Serializable
data class Energy(
    val progress: Progress,
    val restore: Int,
    @SerialName("day_type") val dayType: Int,
    val hour: Int,
    val minute: Int
)

@Serializable
data class Progress(
    val max: Int, val current: Int
)

@Serializable
data class VhsSale(
    @SerialName("sale_state") val saleState: String
)

@Serializable
data class BountyCommission(
    val num: Int, val total: Int
)

@Serializable
data class SurveyPoints(
    val num: Int, val total: Int, @SerialName("is_max_level") val isMaxLevel: Boolean
)

@Serializable
data class Coffee(
    val num: Int, val total: Int
)

@Serializable
data class WeeklyTask(
    @SerialName("refresh_time") val refreshTime: Long,
    @SerialName("cur_point") val curPoint: Int,
    @SerialName("max_point") val maxPoint: Int
)

val stubGameRecordResponse = GameRecordResponse(
    retCode = 0,
    message = "OK",
    data = GameRecordData(
        energy = Energy(
            progress = Progress(max = 240, current = 221),
            restore = 6803,
            dayType = 1,
            hour = 22,
            minute = 43
        ),
        vitality = Progress(max = 400, current = 0),
        vhsSale = VhsSale(saleState = "SaleStateDone"),
        cardSign = "CardSignNo",
        bountyCommission = BountyCommission(num = 0, total = 4),
        surveyPoints = SurveyPoints(num = 0, total = 8000, isMaxLevel = true),
        abyssRefresh = 112191,
        coffee = Coffee(num = 0, total = 0),
        weeklyTask = WeeklyTask(refreshTime = 112191, curPoint = 1050, maxPoint = 1300)
    )
)