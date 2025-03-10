/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.my_agent_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentDetailSkill(
    val level: Int,
    @SerialName("skill_type") val skillType: Int,
    val items: List<MyAgentDetailSkillItem>
)

@Serializable
data class MyAgentDetailSkillItem(
    val title: String, val text: String
)

val stubMyAgentDetailSkill = MyAgentDetailSkill(
    level = 12, skillType = 0, items = listOf(
        MyAgentDetailSkillItem(
            title = "普通攻擊：不許動！",
            text = "交替使用體術、手槍和以太鹿彈，向前方進行至多五段的攻擊，造成物理傷害和以太傷害。"
        )
    )
)