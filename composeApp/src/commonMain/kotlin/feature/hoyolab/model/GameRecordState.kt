/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

data class GameRecordState(
    val nickname: String,
    val server: String,
    val uid: String,
    val profileUrl: String,
    val cardUrl: String,
    val energy: EnergyState,
    val vitality: ProgressState,
    val vhsSale: VhsSaleState,
    val cardSign: String,
    val bountyCommission: BountyCommissionState,
    val surveyPoints: SurveyPointsState,
    val abyssRefresh: Long,
    val coffee: CoffeeState,
    val weeklyTask: WeeklyTaskState,
)

data class EnergyState(
    val progress: ProgressState,
    val restore: Int,
    val dayType: Int,
    val hour: String,
    val minute: String
)

data class ProgressState(
    val max: String, val current: String
)

data class VhsSaleState(
    val saleState: String
)

data class BountyCommissionState(
    val num: String, val total: String
)

data class SurveyPointsState(
    val num: String, val total: String, val isMaxLevel: Boolean
)

data class CoffeeState(
    val num: String, val total: String
)

data class WeeklyTaskState(
    val refreshTime: Long,
    val curPoint: String,
    val maxPoint: String
)

val subGameRecordState = GameRecordState(
    nickname = "mrfatworm",
    server = "Asia",
    uid = "1300051361",
    profileUrl = "https://example.com/profile",
    cardUrl = "https://example.com/card",
    energy = EnergyState(
        progress = ProgressState(max = "240", current = "221"),
        restore = 6803,
        dayType = 1,
        hour = "22",
        minute = "43"
    ),
    vitality = ProgressState(max = "400", current = "0"),
    vhsSale = VhsSaleState(saleState = "SaleStateDone"),
    cardSign = "CardSignNo",
    bountyCommission = BountyCommissionState(num = "0", total = "4"),
    surveyPoints = SurveyPointsState(num = "0", total = "8000", isMaxLevel = true),
    abyssRefresh = 112191,
    coffee = CoffeeState(num = "0", total = "0"),
    weeklyTask = WeeklyTaskState(refreshTime = 112191, curPoint = "1050", maxPoint = "1300")
)