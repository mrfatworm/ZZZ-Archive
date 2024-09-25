/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package ui.data

import utils.ZzzArchiveRarity


data class SimpleListItemState(
    val id: Int = 0,
    val name: String,
    val imgUrl: String?,
    val rarity: ZzzArchiveRarity = ZzzArchiveRarity.One
)

val sampleAgentState = SimpleListItemState(
    name = "11號", imgUrl = "", rarity = ZzzArchiveRarity.Five
)

val sampleWEnginState = SimpleListItemState(
    name = "11號的專武，長字串測試測試測試測試測試",
    imgUrl = "",
    rarity = ZzzArchiveRarity.Five
)

val sampleDriverState = SimpleListItemState(
    name = "震星迪思可", imgUrl = "", rarity = ZzzArchiveRarity.One
)

val sampleAgentsList = listOf(
    sampleAgentState,
    sampleAgentState.copy(rarity = ZzzArchiveRarity.Four),
    sampleAgentState.copy(rarity = ZzzArchiveRarity.Three),
    sampleAgentState.copy(rarity = ZzzArchiveRarity.Two),
    sampleAgentState.copy(rarity = ZzzArchiveRarity.One)
)

val sampleWEnginesList = listOf(
    sampleWEnginState,
    sampleWEnginState.copy(rarity = ZzzArchiveRarity.Four),
    sampleWEnginState.copy(rarity = ZzzArchiveRarity.Three),
    sampleWEnginState.copy(rarity = ZzzArchiveRarity.Two),
    sampleWEnginState.copy(rarity = ZzzArchiveRarity.One)
)

val sampleDrivesList = listOf(
    sampleDriverState, sampleDriverState, sampleDriverState, sampleDriverState, sampleDriverState
)


