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
    val developer: List<Contributor>,
    val uiUxDesigner: List<Contributor>,
    val translation: List<Contributor>,
    val dataIntegration: List<Contributor>,
    val specialThanks: List<Contributor>
)

data class Contributor(val name: String, val description: String = "")

val settingState =
    SettingState(
        isDark = true,
        appVersion = "Luciana 2024.11.13",
        contributors =
            Contributors(
                contributorAmount = 0,
                developer =
                    listOf(
                        Contributor("mrfatworm")
                    ),
                uiUxDesigner =
                    listOf(
                        Contributor("mrfatworm")
                    ),
                translation =
                    listOf(
                        Contributor(name = "mrfatworm", description = "zh, us")
                    ),
                dataIntegration =
                    listOf(
                        Contributor("mrfatworm"),
                        Contributor("rm6alalauau")
                    ),
                specialThanks =
                    listOf(
                        Contributor(name = "Zenless Zone Zero"),
                        Contributor(name = "Zenless Zone Zero Wiki (Fandom)")
                    )
            )
    )
