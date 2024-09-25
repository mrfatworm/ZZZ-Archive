/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package utils

import androidx.compose.ui.graphics.Brush
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import ui.utils.rarityFiveBrush
import ui.utils.rarityFourBrush
import ui.utils.rarityOneBrush
import ui.utils.rarityThreeBrush
import ui.utils.rarityTwoBrush
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.electric
import zzzarchive.composeapp.generated.resources.ether
import zzzarchive.composeapp.generated.resources.fire
import zzzarchive.composeapp.generated.resources.ic_attribute_electric
import zzzarchive.composeapp.generated.resources.ic_attribute_ether
import zzzarchive.composeapp.generated.resources.ic_attribute_fire
import zzzarchive.composeapp.generated.resources.ic_attribute_ice
import zzzarchive.composeapp.generated.resources.ic_attribute_physical
import zzzarchive.composeapp.generated.resources.ice
import zzzarchive.composeapp.generated.resources.physical


enum class ZzzArchiveRarity(val rarity: Int, val color: Brush) {
    One(1, rarityOneBrush),
    Two(2, rarityTwoBrush),
    Three(3, rarityThreeBrush),
    Four(4, rarityFourBrush),
    Five(5, rarityFiveBrush)
}

enum class AgentAttribute(val attribute: StringResource, val iconRes: DrawableResource) {
    Ether(Res.string.ether, Res.drawable.ic_attribute_ether),
    Fire(Res.string.fire, Res.drawable.ic_attribute_fire),
    Ice(Res.string.ice, Res.drawable.ic_attribute_ice),
    Electric(Res.string.electric, Res.drawable.ic_attribute_electric),
    Physical(Res.string.physical, Res.drawable.ic_attribute_physical)
}
