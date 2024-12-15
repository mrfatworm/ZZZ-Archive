/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import feature.agent.model.AgentListItem
import feature.bangboo.model.BangbooListItem
import feature.banner.data.BannerResponse
import feature.cover_image.data.database.CoverImageListItemEntity
import feature.drive.data.database.DrivesListItemEntity
import feature.hoyolab.model.BountyCommissionState
import feature.hoyolab.model.CoffeeState
import feature.hoyolab.model.EnergyState
import feature.hoyolab.model.GameRecordState
import feature.hoyolab.model.ProgressState
import feature.hoyolab.model.SurveyPointsState
import feature.hoyolab.model.VhsSaleState
import feature.hoyolab.model.WeeklyTaskState
import feature.news.model.OfficialNewsState
import feature.pixiv.data.RecentArticle
import feature.wengine.model.WEnginesListItem

data class HomeState(
    val banner: BannerResponse? = null,
    val coverImage: List<CoverImageListItemEntity> = emptyList(),
    val newsList: List<OfficialNewsState> = emptyList(),
    val pixivTopics: List<RecentArticle> = emptyList(),
    val gameRecord: GameRecordState = emptyGameRecordState,
    val signResult: String? = null,
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEnginesListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DrivesListItemEntity> = emptyList()
)


val emptyGameRecordState = GameRecordState(
    hasAccount = false,
    nickname = "----",
    server = "Server",
    uid = "0000000000",
    profileUrl = "",
    cardUrl = "",
    energy = EnergyState(
        progress = ProgressState(max = "240", current = "---"),
        restore = 6803,
        dayType = 1,
        hour = "--",
        minute = "--"
    ),
    vitality = ProgressState(max = "---", current = "400"),
    vhsSale = VhsSaleState(saleState = "???"),
    cardSign = "???",
    bountyCommission = BountyCommissionState(num = "--", total = "3"),
    surveyPoints = SurveyPointsState(num = "----", total = "8000", isMaxLevel = true),
    abyssRefresh = 112191,
    coffee = CoffeeState(num = "--", total = "--"),
    weeklyTask = WeeklyTaskState(refreshTime = 112191, curPoint = "----", maxPoint = "1300")
)

