/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.mapper

import feature.hoyolab.model.agent.CultivateInfo
import feature.hoyolab.model.agent.CultivateInfoResponse
import feature.hoyolab.model.agent.EquipPlanProperty
import feature.hoyolab.model.agent.EquipPlanPropertyResponse
import feature.hoyolab.model.agent.MyAgentAwakenSkill
import feature.hoyolab.model.agent.MyAgentAwakenSkillItemResponse
import feature.hoyolab.model.agent.MyAgentDetail
import feature.hoyolab.model.agent.MyAgentDetailEquip
import feature.hoyolab.model.agent.MyAgentDetailEquipPlan
import feature.hoyolab.model.agent.MyAgentDetailEquipPlanResponse
import feature.hoyolab.model.agent.MyAgentDetailEquipResponse
import feature.hoyolab.model.agent.MyAgentDetailItemResponse
import feature.hoyolab.model.agent.MyAgentDetailProperty
import feature.hoyolab.model.agent.MyAgentDetailPropertyResponse
import feature.hoyolab.model.agent.MyAgentDetailSkill
import feature.hoyolab.model.agent.MyAgentDetailSkillItem
import feature.hoyolab.model.agent.MyAgentDetailSkillItemResponse
import feature.hoyolab.model.agent.MyAgentDetailSkillResponse
import feature.hoyolab.model.agent.MyAgentDetailWeapon
import feature.hoyolab.model.agent.MyAgentDetailWeaponResponse
import feature.hoyolab.model.agent.MyAgentDriveProperty
import feature.hoyolab.model.agent.MyAgentEquipSuit
import feature.hoyolab.model.agent.MyAgentEquipSuitResponse
import feature.hoyolab.model.agent.MyAgentMindscape
import feature.hoyolab.model.agent.MyAgentPropertyResponse
import feature.hoyolab.model.agent.MyAgentRankResponse
import feature.hoyolab.model.agent.MyAgentSkillAwaken
import feature.hoyolab.model.agent.MyAgentSkillAwakenItemResponse
import feature.hoyolab.model.agent.MyAgentSkillAwakenResponse
import feature.hoyolab.model.agent.MyAgentSkillAwakenTier
import feature.hoyolab.model.agent.MyAgentSkin
import feature.hoyolab.model.agent.MyAgentSkinResponse
import utils.findAgentAttributeFromHoYoLab
import utils.findAgentSpecialtyFromHoYoLab
import utils.findAgentSubAttributeFromHoYoLab
import utils.findRarityFromHoYoLab

fun MyAgentDetailItemResponse.toMyAgentDetail(): MyAgentDetail = MyAgentDetail(
    id = id ?: 0,
    name = nameMi18n.orEmpty(),
    level = level ?: 0,
    rank = rank ?: 0,
    imageUrl = roleVerticalPaintingUrl.orEmpty(),
    factionImageUrl = groupIconPath.orEmpty(),
    rarity = findRarityFromHoYoLab(rarity.orEmpty()),
    specialty = findAgentSpecialtyFromHoYoLab(avatarProfession ?: 0),
    attribute = findAgentAttributeFromHoYoLab(elementType ?: 0),
    subAttribute = findAgentSubAttributeFromHoYoLab(subElementType ?: 0),
    equip = equip?.map { it.toMyAgentDetailEquip() } ?: emptyList(),
    weapon = weapon?.toMyAgentDetailWeapon() ?: MyAgentDetailWeapon.Empty,
    properties = properties?.map { it.toMyAgentDetailProperty() } ?: emptyList(),
    skills = skills?.map { it.toMyAgentDetailSkill() } ?: emptyList(),
    equipPlanInfo = equipPlanInfo?.toMyAgentDetailEquipPlan() ?: MyAgentDetailEquipPlan.Empty,
    skins = skinList?.filter { it.unlocked != false }?.map { it.toMyAgentSkin() } ?: emptyList(),
    mindscapes = ranks.toMyAgentMindscapes(),
    skillAwaken = skillAwaken?.toMyAgentSkillAwaken() ?: MyAgentSkillAwaken.Empty
)

fun List<MyAgentRankResponse>?.toMyAgentMindscapes(): List<MyAgentMindscape> = this
    ?.map { it.toMyAgentMindscape() }
    ?.sortedBy { it.id }
    ?: emptyList()

fun MyAgentRankResponse.toMyAgentMindscape(): MyAgentMindscape = MyAgentMindscape(
    id = id ?: 0,
    name = name.orEmpty(),
    description = desc.orEmpty(),
    isUnlocked = isUnlocked ?: false
)

fun MyAgentSkillAwakenResponse.toMyAgentSkillAwaken(): MyAgentSkillAwaken {
    val level = awakenLevel ?: 0
    val tiers = skillAwakenItems?.map { it.toMyAgentSkillAwakenTier(level) } ?: emptyList()
    return if (hasAwakenSystem != true || tiers.isEmpty()) {
        MyAgentSkillAwaken.Empty
    } else {
        MyAgentSkillAwaken.Awaken(
            level = level,
            maxLevel = awakenMaxLevel ?: 0,
            tiers = tiers.sortedBy { it.level }
        )
    }
}

fun MyAgentSkillAwakenItemResponse.toMyAgentSkillAwakenTier(unlockedLevel: Int): MyAgentSkillAwakenTier {
    val level = awakenLevel ?: 0
    return MyAgentSkillAwakenTier(
        level = level,
        name = levelShowName.orEmpty(),
        // The response has no per-tier flag, so a tier counts as unlocked up to awaken_level.
        isUnlocked = level <= unlockedLevel,
        skills = awakenSkillItems?.map { it.toMyAgentAwakenSkill() } ?: emptyList()
    )
}

fun MyAgentAwakenSkillItemResponse.toMyAgentAwakenSkill(): MyAgentAwakenSkill = MyAgentAwakenSkill(
    skillType = skillType ?: 0,
    summary = awakenSimpleInfo.orEmpty(),
    items = skillItems?.map { it.toMyAgentDetailSkillItem() } ?: emptyList()
)

fun MyAgentDetailSkillItemResponse.toMyAgentDetailSkillItem(): MyAgentDetailSkillItem = MyAgentDetailSkillItem(
    title = title.orEmpty(),
    text = text.orEmpty(),
    isAwaken = awaken ?: false
)

fun MyAgentSkinResponse.toMyAgentSkin(): MyAgentSkin = MyAgentSkin(
    id = skinId ?: 0,
    name = skinName.orEmpty(),
    imageUrl = skinVerticalPaintingUrl.orEmpty(),
    squareImageUrl = skinSquareUrl.orEmpty(),
    isOriginal = isOriginal ?: false
)

fun MyAgentDetailEquipResponse.toMyAgentDetailEquip(): MyAgentDetailEquip = MyAgentDetailEquip(
    id = id ?: 0,
    level = level ?: 0,
    name = name.orEmpty(),
    iconUrl = icon.orEmpty(),
    rarity = rarity.orEmpty(),
    subProperties = properties?.map { it.toMyAgentProperty() } ?: emptyList(),
    mainProperties = mainProperties?.map { it.toMyAgentProperty() } ?: emptyList(),
    equipSuit = equipSuit?.toMyAgentEquipSuit() ?: MyAgentEquipSuit.Empty,
    equipmentType = equipmentType ?: 0,
    invalidPropertyCnt = invalidPropertyCnt ?: 0,
    allHit = allHit ?: false
)

fun MyAgentDetailWeaponResponse.toMyAgentDetailWeapon(): MyAgentDetailWeapon = MyAgentDetailWeapon.MyAgentWeapon(
    id = id ?: 0,
    level = level ?: 0,
    name = name.orEmpty(),
    star = star ?: 1,
    iconUrl = icon.orEmpty(),
    rarity = rarity.orEmpty(),
    properties = properties?.map { it.toMyAgentProperty() } ?: emptyList(),
    mainProperties = mainProperties?.map { it.toMyAgentProperty() } ?: emptyList(),
    talentTitle = talentTitle.orEmpty(),
    talentContent = talentContent.orEmpty(),
    profession = profession ?: 0
)

fun MyAgentDetailPropertyResponse.toMyAgentDetailProperty(): MyAgentDetailProperty = MyAgentDetailProperty(
    name = propertyName.orEmpty(),
    id = propertyId ?: 0,
    base = base.orEmpty(),
    add = add.orEmpty(),
    final = final.orEmpty()
)

fun MyAgentDetailSkillResponse.toMyAgentDetailSkill(): MyAgentDetailSkill = MyAgentDetailSkill(
    level = level ?: 0,
    skillType = skillType ?: 0,
    items = items?.map { it.toMyAgentDetailSkillItem() } ?: emptyList()
)

fun MyAgentDetailEquipPlanResponse.toMyAgentDetailEquipPlan(): MyAgentDetailEquipPlan =
    MyAgentDetailEquipPlan.MyAgentEquipPlan(
        type = type ?: 0,
        gameDefaultPropertyList = gameDefault?.propertyList?.map { it.toEquipPlanProperty() } ?: emptyList(),
        customInfoPropertyList = customInfo?.propertyList?.map { it.toEquipPlanProperty() } ?: emptyList(),
        cultivateInfo = cultivateInfo?.toCultivateInfo() ?: CultivateInfo(
            name = "",
            planId = "",
            isDelete = false,
            oldPlan = false
        ),
        validPropertyCnt = validPropertyCnt ?: 0,
        planOnlySpecialProperty = planOnlySpecialProperty ?: false,
        planEffectivePropertyList = planEffectivePropertyList?.map { it.toEquipPlanProperty() } ?: emptyList(),
        equipRating = equipRating
    )

fun MyAgentPropertyResponse.toMyAgentProperty(): MyAgentDriveProperty = MyAgentDriveProperty(
    name = propertyName.orEmpty(),
    id = propertyId ?: 0,
    base = base.orEmpty(),
    level = level ?: 0,
    valid = valid ?: false,
    systemId = systemId ?: 0,
    add = add ?: 0
)

fun MyAgentEquipSuitResponse.toMyAgentEquipSuit(): MyAgentEquipSuit = MyAgentEquipSuit.EquipSuit(
    suitId = suitId ?: 0,
    name = name.orEmpty(),
    own = own ?: 0,
    desc1 = desc1.orEmpty(),
    desc2 = desc2.orEmpty()
)

fun EquipPlanPropertyResponse.toEquipPlanProperty(): EquipPlanProperty = EquipPlanProperty(
    id = id ?: 0,
    name = name.orEmpty(),
    fullName = fullName.orEmpty(),
    systemId = systemId ?: 0,
    isSelect = isSelect ?: false
)

fun CultivateInfoResponse.toCultivateInfo(): CultivateInfo = CultivateInfo(
    name = name.orEmpty(),
    planId = planId.orEmpty(),
    isDelete = isDelete ?: false,
    oldPlan = oldPlan ?: false
)
