/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.model

import feature.agent.model.LevelMaterial
import feature.agent.model.NameAndValue
import utils.AgentSpecialty
import utils.ZzzRarity

data class WEngineDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty,
    val background: String,
    val atk: Int,
    val stat: NameAndValue,
    val skill: String,
    val levelMaterials: List<LevelMaterial>
)

val stubWEngineDetail = WEngineDetail(
    id = 47,
    name = "玉壺青冰",
    imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset//W-Engine/Image/47.webp",
    isLeak = false,
    rarity = ZzzRarity.RARITY_S,
    specialty = AgentSpecialty.Stun,
    background = "參考「 鈺偶 」的戰鬥方式與能力，為其量身訂製的擊破型音擎。其儲能內蘊，衍化後衝擊於外。\n質地模擬古文明追捧的珍貴工藝，燒成則溫潤清雅，碎裂則剛硬銳利。壺中神鳥花紋獨特、如名窯冰裂……但其實是手工繪製的。\n\n「保温效果很好，在空洞中也可以隨時喝上熱水。」一一青衣，面對研究者回訪時認真講述意見",
    atk = 713,
    stat = NameAndValue(
        name = "衝擊力", value = "18%"
    ),
    skill = "[普通攻擊]命中敵人時，裝備者的衝擊力提升0.7％/0.88%/1.05%/1.22%/1.4%，最多疊加30層，持續8秒，每層效果單獨結算持續時間。裝備者的衝擊力大於等於15時，[普通攻撃]、[衝刺攻擊]和[閃避反擊]造成的傷害提升20％/23%/26%/29%/32%。",
    levelMaterials = listOf(
        LevelMaterial(
            id = 1, amount = 400000
        ), LevelMaterial(
            id = 10, amount = 188
        ), LevelMaterial(
            id = 35, amount = 4
        ), LevelMaterial(
            id = 36, amount = 32
        ), LevelMaterial(
            id = 37, amount = 30
        )
    )
)
