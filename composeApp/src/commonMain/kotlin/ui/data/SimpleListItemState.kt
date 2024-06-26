/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.data

import org.jetbrains.compose.resources.DrawableResource
import utils.ZzzArchiveRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.compose_multiplatform


data class SimpleListItemState(
    val id: Long = 0,
    val name: String,
    val imageRes: DrawableResource?,
    val rarity: ZzzArchiveRarity = ZzzArchiveRarity.One
)

val stubCharacterState = SimpleListItemState(
    name = "ZZZ Boy", imageRes = Res.drawable.compose_multiplatform, rarity = ZzzArchiveRarity.Five
)

val stubWeaponState = SimpleListItemState(
    name = "ZZZ Boy's weapon', long string test long string test",
    imageRes = Res.drawable.compose_multiplatform,
    rarity = ZzzArchiveRarity.Five
)

val stubArtifactState = SimpleListItemState(
    name = "DVD", imageRes = Res.drawable.compose_multiplatform, rarity = ZzzArchiveRarity.One
)

val stubCharactersList = listOf(
    stubCharacterState,
    stubCharacterState.copy(rarity = ZzzArchiveRarity.Four),
    stubCharacterState.copy(rarity = ZzzArchiveRarity.Three),
    stubCharacterState.copy(rarity = ZzzArchiveRarity.Two),
    stubCharacterState.copy(rarity = ZzzArchiveRarity.One)
)

val stubWeaponsList = listOf(
    stubWeaponState,
    stubWeaponState.copy(rarity = ZzzArchiveRarity.Four),
    stubWeaponState.copy(rarity = ZzzArchiveRarity.Three),
    stubWeaponState.copy(rarity = ZzzArchiveRarity.Two),
    stubWeaponState.copy(rarity = ZzzArchiveRarity.One)
)

val stubArtifactsList = listOf(
    stubArtifactState, stubArtifactState, stubArtifactState, stubArtifactState, stubArtifactState
)

