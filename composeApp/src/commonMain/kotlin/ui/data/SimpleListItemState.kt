/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package ui.data


data class SimpleListItemState(
    val id: Int = 0,
    val name: String,
    val imgUrl: String?,
    val rarity: Int = 1
)

val sampleAgentState = SimpleListItemState(
    name = "11號", imgUrl = "", rarity = 5
)

val sampleWEnginState = SimpleListItemState(
    name = "11號的專武，長字串測試測試測試測試測試",
    imgUrl = "",
    rarity = 5
)

val sampleDriverState = SimpleListItemState(
    name = "震星迪思可", imgUrl = "", rarity = 5
)

val sampleAgentsList = listOf(
    sampleAgentState,
    sampleAgentState.copy(rarity = 4),
    sampleAgentState.copy(rarity = 3),
    sampleAgentState.copy(rarity = 2),
    sampleAgentState.copy(rarity = 1)
)

val sampleWEnginesList = listOf(
    sampleWEnginState,
    sampleWEnginState.copy(rarity = 4),
    sampleWEnginState.copy(rarity = 3),
    sampleWEnginState.copy(rarity = 2),
    sampleWEnginState.copy(rarity = 1)
)

val sampleDrivesList = listOf(
    sampleDriverState, sampleDriverState, sampleDriverState, sampleDriverState, sampleDriverState
)


