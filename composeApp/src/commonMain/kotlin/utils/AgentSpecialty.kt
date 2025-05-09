package utils

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.anomaly
import zzzarchive.composeapp.generated.resources.attack
import zzzarchive.composeapp.generated.resources.defense
import zzzarchive.composeapp.generated.resources.ic_help
import zzzarchive.composeapp.generated.resources.ic_specialty_anomaly
import zzzarchive.composeapp.generated.resources.ic_specialty_attack
import zzzarchive.composeapp.generated.resources.ic_specialty_defense
import zzzarchive.composeapp.generated.resources.ic_specialty_stun
import zzzarchive.composeapp.generated.resources.ic_specialty_support
import zzzarchive.composeapp.generated.resources.stun
import zzzarchive.composeapp.generated.resources.support
import zzzarchive.composeapp.generated.resources.unknown

enum class AgentSpecialty(val textRes: StringResource, val iconRes: DrawableResource) {
    Attack(Res.string.attack, Res.drawable.ic_specialty_attack),
    Stun(Res.string.stun, Res.drawable.ic_specialty_stun),
    Support(Res.string.support, Res.drawable.ic_specialty_support),
    Anomaly(Res.string.anomaly, Res.drawable.ic_specialty_anomaly),
    Defense(Res.string.defense, Res.drawable.ic_specialty_defense),
    None(Res.string.unknown, Res.drawable.ic_help)
}

fun findAgentSpecialty(specialty: String): AgentSpecialty {
    return AgentSpecialty.entries.find { it.name.lowercase() == specialty }
        ?: AgentSpecialty.None
}

fun findAgentSpecialtyFromHoYoLab(specialtyId: Int): AgentSpecialty {
    return when (specialtyId) {
        1 -> AgentSpecialty.Attack
        2 -> AgentSpecialty.Stun
        3 -> AgentSpecialty.Anomaly
        4 -> AgentSpecialty.Support
        5 -> AgentSpecialty.Defense
        else -> AgentSpecialty.None
    }
}