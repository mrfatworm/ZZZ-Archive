/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: MIT License
 */

package utils

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.electric
import zzzarchive.composeapp.generated.resources.ether
import zzzarchive.composeapp.generated.resources.fire
import zzzarchive.composeapp.generated.resources.ic_attribute_electric
import zzzarchive.composeapp.generated.resources.ic_attribute_ether
import zzzarchive.composeapp.generated.resources.ic_attribute_fire
import zzzarchive.composeapp.generated.resources.ic_attribute_ice
import zzzarchive.composeapp.generated.resources.ic_attribute_physical
import zzzarchive.composeapp.generated.resources.ic_help
import zzzarchive.composeapp.generated.resources.ice
import zzzarchive.composeapp.generated.resources.physical
import zzzarchive.composeapp.generated.resources.unknown


enum class AgentAttribute(val textRes: StringResource, val iconRes: DrawableResource) {
    Ether(Res.string.ether, Res.drawable.ic_attribute_ether),
    Fire(Res.string.fire, Res.drawable.ic_attribute_fire),
    Ice(Res.string.ice, Res.drawable.ic_attribute_ice),
    Electric(Res.string.electric, Res.drawable.ic_attribute_electric),
    Physical(Res.string.physical, Res.drawable.ic_attribute_physical),
    None(Res.string.unknown, Res.drawable.ic_help)
}

fun findAgentAttribute(attribute: String): AgentAttribute {
    return AgentAttribute.entries.find { it.name.lowercase().lowercase() == attribute }
        ?: AgentAttribute.None
}

fun findAgentAttributeFromHoYoLab(attributeId: Int): AgentAttribute {
    return when (attributeId) {
        200 -> AgentAttribute.Physical
        201 -> AgentAttribute.Fire
        202 -> AgentAttribute.Ice
        203 -> AgentAttribute.Electric
        205 -> AgentAttribute.Ether
        else -> AgentAttribute.None
    }
}