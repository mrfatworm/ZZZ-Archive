/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.model

import utils.Language


data class SettingState(
    val isDark: Boolean,
    val language: Language = Language.English,
    val uiScale: Float = 1f,
    val fontScale: Float = 1f,
    val appVersion: String,
    val contributors: Contributors
)

data class Contributors(
    val contributorAmount: Int,
    val author: List<Contributor>,
    val developer: List<Contributor>,
    val uiUxDesigner: List<Contributor>,
    val translation: List<Contributor>,
    val dataIntegration: List<Contributor>,
    val bannerArtists: List<Contributor>,
    val specialThanks: List<Contributor>
)

data class Contributor(
    val name: String,
    val description: String = ""
)

val settingState = SettingState(
    isDark = true,
    appVersion = "Luciana 2024.11.13",
    contributors = Contributors(
        contributorAmount = 4, author = listOf(
            Contributor("mrfatworm"),
        ), developer = listOf(
            Contributor("mrfatworm"),
        ), uiUxDesigner = listOf(
            Contributor("mrfatworm"),
        ), translation = listOf(
            Contributor("mrfatworm", "zh, us"),
        ), dataIntegration = listOf(
            Contributor("mrfatworm"),
            Contributor("rm6alalauau")
        ), bannerArtists = listOf(
            Contributor("EDIBLE", "リン　心象映画"),
            Contributor("咖卡CAthenal", "工作时间到！"),
        ), specialThanks = listOf(
            Contributor("Zenless Zone Zero"), Contributor("Zenless Zone Zero Wiki (Fandom)")
        )
    )
)


