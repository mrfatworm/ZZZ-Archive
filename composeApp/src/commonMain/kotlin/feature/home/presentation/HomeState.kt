/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import feature.banner.data.BannerResponse
import feature.cover.data.database.CoverImageListItemEntity
import feature.forum.model.AllForumState
import feature.hoyolab.model.BountyCommissionState
import feature.hoyolab.model.CoffeeState
import feature.hoyolab.model.EnergyState
import feature.hoyolab.model.GameRecordState
import feature.hoyolab.model.ProgressState
import feature.hoyolab.model.SurveyPointsState
import feature.hoyolab.model.VhsSaleState
import feature.hoyolab.model.WeeklyTaskState
import feature.news.model.OfficialNewsListItem
import feature.pixiv.model.PixivArticleItem

data class HomeState(
    val banner: BannerResponse? = null,
    val coverImage: List<CoverImageListItemEntity> = emptyList(),
    val newsList: List<OfficialNewsListItem> = emptyList(),
    val pixivTopics: List<PixivArticleItem> = emptyList(),
    val gameRecord: GameRecordState = emptyGameRecordState,
    val signResult: String? = null,
    val allForum: AllForumState = emptyAllForumState
)

val emptyGameRecordState =
    GameRecordState(
        hasAccount = false,
        nickname = "----",
        server = "Server",
        uid = "0000000000",
        profileUrl = "",
        cardUrl = "",
        energy =
        EnergyState(
            progress = ProgressState(max = "240", current = "---"),
            restore = 6803,
            dayType = 1,
            hour = "--",
            minute = "--"
        ),
        vitality = ProgressState(max = "?", current = "?"),
        vhsSale = VhsSaleState(saleState = "???"),
        cardSign = "???",
        bountyCommission = BountyCommissionState(num = "?", total = "?"),
        surveyPoints = SurveyPointsState(num = "?", total = "?", isMaxLevel = true),
        abyssRefresh = 112191,
        coffee = CoffeeState(num = "?", total = "?"),
        weeklyTask = WeeklyTaskState(refreshTime = 112191, curPoint = "?", maxPoint = "?")
    )

val emptyAllForumState =
    AllForumState(
        reddit = emptyList(),
        bahamut = emptyList(),
        ptt = emptyList(),
        twitter = emptyList(),
        nga = emptyList()
    )
