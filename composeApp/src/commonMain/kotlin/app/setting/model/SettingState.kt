/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting.model


data class SettingState(
    val author: String,
    val developer: List<Contributor>,
    val uiUxDesigner: List<Contributor>,
    val translation: List<Contributor>,
    val dataIntegration: List<Contributor>,
    val bannerArtists: List<Contributor>,
    val specialThanks: List<Contributor>,
)

data class Contributor(
    val name: String,
    val description: String?,
)

val settingState = SettingState(
    author = "mrfatworm",
    developer = listOf(
        Contributor("mrfatworm", null),
    ),
    uiUxDesigner = listOf(
        Contributor("mrfatworm", null),
    ),
    translation = listOf(
        Contributor("mrfatworm", "zh, us"),
    ),
    dataIntegration = listOf(
        Contributor("mrfatworm", null),
    ),
    bannerArtists = listOf(
        Contributor("---", null),
    ),
    specialThanks = listOf(
        Contributor("Zenless Zone Zero", null),
        Contributor("Zenless Zone Zero Wiki (Fandom)", null)
    )
)


