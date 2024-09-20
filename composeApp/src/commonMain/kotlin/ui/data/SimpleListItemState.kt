/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package ui.data

import org.jetbrains.compose.resources.DrawableResource
import utils.ZzzArchiveRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.img_agent_11
import zzzarchive.composeapp.generated.resources.img_driver
import zzzarchive.composeapp.generated.resources.img_w_engine_brimstone


data class SimpleListItemState(
    val id: Long = 0,
    val name: String,
    val imageRes: DrawableResource?,
    val rarity: ZzzArchiveRarity = ZzzArchiveRarity.One
)

val sampleAgentState = SimpleListItemState(
    name = "11號", imageRes = Res.drawable.img_agent_11, rarity = ZzzArchiveRarity.Five
)

val sampleWEnginState = SimpleListItemState(
    name = "11號的專武，長字串測試測試測試測試測試",
    imageRes = Res.drawable.img_w_engine_brimstone,
    rarity = ZzzArchiveRarity.Five
)

val sampleDriverState = SimpleListItemState(
    name = "震星迪思可", imageRes = Res.drawable.img_driver, rarity = ZzzArchiveRarity.One
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

val sampleDriversList = listOf(
    sampleDriverState, sampleDriverState, sampleDriverState, sampleDriverState, sampleDriverState
)


