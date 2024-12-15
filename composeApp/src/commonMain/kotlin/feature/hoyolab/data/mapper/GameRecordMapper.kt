/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.mapper

import feature.hoyolab.model.BountyCommissionState
import feature.hoyolab.model.CoffeeState
import feature.hoyolab.model.EnergyState
import feature.hoyolab.model.GameRecordData
import feature.hoyolab.model.GameRecordState
import feature.hoyolab.model.ProgressState
import feature.hoyolab.model.SurveyPointsState
import feature.hoyolab.model.VhsSaleState
import feature.hoyolab.model.WeeklyTaskState

fun GameRecordData.toGameRecordState(
    nickname: String, server: String, uid: String, profileUrl: String, cardUrl: String
): GameRecordState {
    return GameRecordState(
        nickname = nickname,
        server = server,
        uid = uid,
        profileUrl = profileUrl,
        cardUrl = cardUrl,
        energy = EnergyState(
            progress = ProgressState(
                max = energy.progress.max.toString(), current = energy.progress.current.toString()
            ),
            restore = energy.restore,
            dayType = energy.dayType,
            hour = energy.hour.toString(),
            minute = energy.minute.toString()
        ),
        vitality = ProgressState(
            max = vitality.max.toString(), current = vitality.current.toString()
        ),
        vhsSale = VhsSaleState(
            saleState = vhsSale.saleState
        ),
        cardSign = cardSign,
        bountyCommission = BountyCommissionState(
            num = bountyCommission?.num?.toString() ?: "?",
            total = bountyCommission?.total?.toString() ?: "?"
        ),
        abyssRefresh = abyssRefresh,
        coffee = CoffeeState(
            num = coffee?.num?.toString() ?: "?", total = coffee?.total.toString()
        ),
        surveyPoints = SurveyPointsState(
            num = surveyPoints?.num?.toString() ?: "?",
            total = surveyPoints?.total?.toString() ?: "?",
            isMaxLevel = surveyPoints?.isMaxLevel ?: false
        ),
        weeklyTask = WeeklyTaskState(
            refreshTime = weeklyTask?.refreshTime ?: 0L,
            curPoint = weeklyTask?.curPoint?.toString() ?: "?",
            maxPoint = weeklyTask?.maxPoint?.toString() ?: "?"
        )
    )
}