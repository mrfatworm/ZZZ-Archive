/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: MIT License
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
import zzzarchive.composeapp.generated.resources.ic_attribute_lumiflux
import zzzarchive.composeapp.generated.resources.ic_attribute_physical
import zzzarchive.composeapp.generated.resources.ic_attribute_wind
import zzzarchive.composeapp.generated.resources.ic_help
import zzzarchive.composeapp.generated.resources.ice
import zzzarchive.composeapp.generated.resources.lumiflux
import zzzarchive.composeapp.generated.resources.physical
import zzzarchive.composeapp.generated.resources.unknown
import zzzarchive.composeapp.generated.resources.wind

enum class AgentAttribute(val textRes: StringResource, val iconRes: DrawableResource) {
    Ether(Res.string.ether, Res.drawable.ic_attribute_ether),
    Fire(Res.string.fire, Res.drawable.ic_attribute_fire),
    Ice(Res.string.ice, Res.drawable.ic_attribute_ice),
    Electric(Res.string.electric, Res.drawable.ic_attribute_electric),
    Physical(Res.string.physical, Res.drawable.ic_attribute_physical),
    Wind(Res.string.wind, Res.drawable.ic_attribute_wind),
    Lumiflux(Res.string.lumiflux, Res.drawable.ic_attribute_lumiflux),
    None(Res.string.unknown, Res.drawable.ic_help);

    fun getColor(colorScheme: ColorScheme): Color = when (this) {
        Ether -> colorScheme.ether
        Fire -> colorScheme.fire
        Ice -> colorScheme.ice
        Electric -> colorScheme.electric
        Physical -> colorScheme.physical
        Wind -> colorScheme.wind
        Lumiflux -> colorScheme.lumiflux
        else -> colorScheme.onSurfaceVariant
    }
}

fun findAgentAttribute(attribute: String): AgentAttribute =
    AgentAttribute.entries.find { it.name.lowercase().lowercase() == attribute }
        ?: AgentAttribute.None

fun findAgentAttributeFromHoYoLab(attributeId: Int): AgentAttribute = when (attributeId) {
    200 -> AgentAttribute.Physical
    201 -> AgentAttribute.Fire
    202 -> AgentAttribute.Ice
    203 -> AgentAttribute.Electric
    204 -> AgentAttribute.Wind
    205 -> AgentAttribute.Ether
    300 -> AgentAttribute.Lumiflux
    else -> AgentAttribute.None
}
