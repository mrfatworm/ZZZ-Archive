/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentDetailSkillResponse(
    val level: Int? = null,
    @SerialName("skill_type") val skillType: Int? = null,
    val items: List<MyAgentDetailSkillItemResponse>? = null
)

@Serializable
data class MyAgentDetailSkillItemResponse(
    val title: String? = null,
    val text: String? = null,
    val awaken: Boolean? = null
)

@Serializable
data class MyAgentSkillAwakenResponse(
    @SerialName("has_awaken_system") val hasAwakenSystem: Boolean? = null,
    @SerialName("awaken_level") val awakenLevel: Int? = null,
    @SerialName("awaken_max_level") val awakenMaxLevel: Int? = null,
    @SerialName("skill_awaken_items") val skillAwakenItems: List<MyAgentSkillAwakenItemResponse>? = null
)

@Serializable
data class MyAgentSkillAwakenItemResponse(
    @SerialName("awaken_level") val awakenLevel: Int? = null,
    @SerialName("level_show_name") val levelShowName: String? = null,
    @SerialName("awaken_skill_items") val awakenSkillItems: List<MyAgentAwakenSkillItemResponse>? = null
)

@Serializable
data class MyAgentAwakenSkillItemResponse(
    @SerialName("skill_type") val skillType: Int? = null,
    @SerialName("awaken_simple_info") val awakenSimpleInfo: String? = null,
    @SerialName("skill_items") val skillItems: List<MyAgentDetailSkillItemResponse>? = null
)

val stubMyAgentDetailSkillResponse =
    MyAgentDetailSkillResponse(
        level = 12,
        skillType = 0,
        items =
            listOf(
                MyAgentDetailSkillItemResponse(
                    title = "普通攻擊：不許動！",
                    text = "交替使用體術、手槍和以太鹿彈，向前方進行至多五段的攻擊，造成<color=#F0D12B>物理傷害</color>和<color=#98EFF0>以太傷害</color>。",
                    awaken = false
                )
            )
    )

val stubMyAgentSkillAwakenResponse =
    MyAgentSkillAwakenResponse(
        hasAwakenSystem = true,
        awakenLevel = 2,
        awakenMaxLevel = 6,
        skillAwakenItems =
            listOf(
                MyAgentSkillAwakenItemResponse(
                    awakenLevel = 1,
                    levelShowName = "大掃除 I",
                    awakenSkillItems =
                        listOf(
                            MyAgentAwakenSkillItemResponse(
                                skillType = 0,
                                awakenSimpleInfo = "新增：[普通攻擊：晨間清掃]",
                                skillItems =
                                    listOf(
                                        MyAgentDetailSkillItemResponse(
                                            title = "普通攻擊：晨間清掃",
                                            text = "進入<color=#FFFFFF>[一塵不染]</color>狀態，持續13秒。",
                                            awaken = true
                                        )
                                    )
                            )
                        )
                ),
                MyAgentSkillAwakenItemResponse(
                    awakenLevel = 2,
                    levelShowName = "大掃除 II",
                    awakenSkillItems =
                        listOf(
                            MyAgentAwakenSkillItemResponse(
                                skillType = 5,
                                awakenSimpleInfo = "新增：[潛能覺醒：完美侍奉]",
                                skillItems =
                                    listOf(
                                        MyAgentDetailSkillItemResponse(
                                            title = "潛能覺醒：完美侍奉",
                                            text = "穿透率提升<color=#2BAD00>1.6%</color>。",
                                            awaken = true
                                        )
                                    )
                            )
                        )
                )
            )
    )
