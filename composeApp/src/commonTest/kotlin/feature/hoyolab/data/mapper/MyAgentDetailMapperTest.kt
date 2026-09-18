/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.mapper

import feature.hoyolab.model.agent.MyAgentDetailEquipPlan
import feature.hoyolab.model.agent.MyAgentDetailWeapon
import feature.hoyolab.model.agent.MyAgentEquipSuit
import feature.hoyolab.model.agent.MyAgentSkillAwaken
import feature.hoyolab.model.agent.emptyMyAgentDetailWeaponResponse
import feature.hoyolab.model.agent.stubEquipResponse
import feature.hoyolab.model.agent.stubMyAgentDetailResponse
import feature.hoyolab.model.agent.stubMyAgentDetailSkillResponse
import feature.hoyolab.model.agent.stubMyAgentDetailWeaponResponse
import feature.hoyolab.model.agent.stubMyAgentSkillAwakenResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue
import utils.AgentSubAttribute

class MyAgentDetailMapperTest {
    private val agentResponse = stubMyAgentDetailResponse.data!!.avatarList!!.first()

    @Test
    fun `Maps every owned outfit and flags the original one`() {
        val skins = agentResponse.toMyAgentDetail().skins
        assertEquals(2, skins.size)
        assertEquals(1, skins.count { it.isOriginal })
        assertTrue(skins.all { it.imageUrl.isNotEmpty() && it.squareImageUrl.isNotEmpty() })
    }

    @Test
    fun `Locked outfits are dropped`() {
        val locked = agentResponse.skinList!!.map { it.copy(unlocked = false) }
        assertEquals(0, agentResponse.copy(skinList = locked).toMyAgentDetail().skins.size)
    }

    @Test
    fun `Mindscapes are mapped in id order with their unlocked flag`() {
        val mindscapes = agentResponse.toMyAgentDetail().mindscapes
        assertEquals(listOf(1, 2), mindscapes.map { it.id })
        assertEquals(listOf(true, false), mindscapes.map { it.isUnlocked })
        assertEquals("熾芒", mindscapes.first().name)
        assertTrue(mindscapes.first().description.isNotEmpty())
    }

    @Test
    fun `Awakening tiers count as unlocked up to awaken_level`() {
        val awaken = agentResponse.toMyAgentDetail().skillAwaken
        assertIs<MyAgentSkillAwaken.Awaken>(awaken)
        assertEquals(2, awaken.level)
        assertEquals(6, awaken.maxLevel)
        assertEquals(listOf(1, 2), awaken.tiers.map { it.level })
        assertEquals(listOf(true, true), awaken.tiers.map { it.isUnlocked })
        assertTrue(awaken.tiers.first().skills.first().items.all { it.isAwaken })
    }

    @Test
    fun `An agent without the awakening system maps to Empty`() {
        val without = agentResponse.copy(skillAwaken = stubMyAgentSkillAwakenResponse.copy(hasAwakenSystem = false))
        assertEquals(MyAgentSkillAwaken.Empty, without.toMyAgentDetail().skillAwaken)
        assertEquals(MyAgentSkillAwaken.Empty, agentResponse.copy(skillAwaken = null).toMyAgentDetail().skillAwaken)
    }

    @Test
    fun `Only the sub element types confirmed against live data are named`() {
        fun subAttributeOf(id: Int) = agentResponse.copy(subElementType = id).toMyAgentDetail().subAttribute
        assertEquals(AgentSubAttribute.Frost, subAttributeOf(1))
        assertEquals(AgentSubAttribute.AuricInk, subAttributeOf(2))
        assertEquals(AgentSubAttribute.None, subAttributeOf(0))
        assertEquals(AgentSubAttribute.None, subAttributeOf(4))
    }

    @Test
    fun `Skill items carry their description and awakening flag`() {
        val skill = stubMyAgentDetailSkillResponse.toMyAgentDetailSkill()
        assertEquals("普通攻擊：不許動！", skill.items.single().title)
        assertTrue(skill.items.single().text.isNotEmpty())
        assertEquals(false, skill.items.single().isAwaken)
    }

    @Test
    fun `Drive disc set effects and their piece count survive mapping`() {
        val suit = stubEquipResponse.toMyAgentDetailEquip().equipSuit
        assertIs<MyAgentEquipSuit.EquipSuit>(suit)
        assertEquals(stubEquipResponse.equipSuit!!.name, suit.name)
        assertEquals(4, suit.own)
        // Both effects are always present; the dialog decides which is live from `own`.
        assertTrue(suit.desc1.isNotEmpty())
        assertTrue(suit.desc2.isNotEmpty())
    }

    @Test
    fun `A disc with no set maps to Empty so it stays unclickable`() {
        val suit = stubEquipResponse.copy(equipSuit = null).toMyAgentDetailEquip().equipSuit
        assertEquals(MyAgentEquipSuit.Empty, suit)
    }

    @Test
    fun `W-Engine talent text survives mapping`() {
        val weapon = stubMyAgentDetailWeaponResponse.toMyAgentDetailWeapon()
        assertIs<MyAgentDetailWeapon.MyAgentWeapon>(weapon)
        assertEquals("束縛上鎖", weapon.talentTitle)
        assertTrue(weapon.talentContent.contains("<color="))
    }

    @Test
    fun `A W-Engine with no talent text maps to an empty talent`() {
        val weapon = emptyMyAgentDetailWeaponResponse.toMyAgentDetailWeapon()
        assertIs<MyAgentDetailWeapon.MyAgentWeapon>(weapon)
        assertEquals("", weapon.talentContent)
    }

    @Test
    fun `Effective sub stat hits are the roll counts of the valid sub stats`() {
        val equip = stubEquipResponse.toMyAgentDetailEquip()
        // HoYoLab counts a sub stat's `level` as its roll count, and sums that over the valid ones
        // to build equip_plan_info.valid_property_cnt. The disc card must use the same measure.
        val expected = stubEquipResponse.properties!!.filter { it.valid == true }.sumOf { it.level ?: 0 }
        assertEquals(expected, equip.subProperties.filter { it.valid }.sumOf { it.level })
    }

    @Test
    fun `Build score and painting colours survive mapping`() {
        val detail = agentResponse.toMyAgentDetail()
        assertEquals("#28c79d", detail.paintingColor)
        assertTrue(detail.skins.all { it.paintingColor == "#28c79d" })
        val plan = detail.equipPlanInfo
        assertIs<MyAgentDetailEquipPlan.MyAgentEquipPlan>(plan)
        assertEquals("ER_SS", plan.equipRating)
        assertEquals(81.3, plan.equipRatingScore)
    }

    @Test
    fun `A response without a build score maps to null rather than zero`() {
        val planWithoutScore = agentResponse.equipPlanInfo!!.copy(equipRatingScore = null)
        val withoutScore = agentResponse.copy(equipPlanInfo = planWithoutScore)
        val plan = withoutScore.toMyAgentDetail().equipPlanInfo
        assertIs<MyAgentDetailEquipPlan.MyAgentEquipPlan>(plan)
        assertEquals(null, plan.equipRatingScore)
    }
}
