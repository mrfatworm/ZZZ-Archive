/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package home.model

import ui.data.SimpleListItemState
import ui.data.stubArtifactsList
import ui.data.stubCharactersList
import ui.data.stubWeaponsList

data class HomeState(
    val activityImageUrl: String = "",
    val charactersList: List<SimpleListItemState> = emptyList(),
    val weaponsList: List<SimpleListItemState> = emptyList(),
    val artifactsList: List<SimpleListItemState> = emptyList()
)


val stubHomeState = HomeState(
    activityImageUrl = "",
    charactersList = stubCharactersList + stubCharactersList + stubCharactersList,
    weaponsList = stubWeaponsList,
    artifactsList = stubArtifactsList
)

