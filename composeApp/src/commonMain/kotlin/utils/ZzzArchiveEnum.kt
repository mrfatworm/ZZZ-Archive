/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package utils

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import ui.theme.ColorScheme
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


enum class ZzzRarity(val level: Int, val color: Color) {
    One(1, ColorScheme().imageRarity1),
    Two(2, ColorScheme().imageRarity2),
    Three(3, ColorScheme().imageRarity3),
    Four(4, ColorScheme().imageRarity4),
    Five(5, ColorScheme().imageRarity5)
}

enum class AgentAttribute(val textRes: StringResource, val iconRes: DrawableResource) {
    Ether(Res.string.ether, Res.drawable.ic_attribute_ether),
    Fire(Res.string.fire, Res.drawable.ic_attribute_fire),
    Ice(Res.string.ice, Res.drawable.ic_attribute_ice),
    Electric(Res.string.electric, Res.drawable.ic_attribute_electric),
    Physical(Res.string.physical, Res.drawable.ic_attribute_physical)
}