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
data class MyAgentDetailSkillItemResponse(val title: String? = null, val text: String? = null)

val stubMyAgentDetailSkillResponse =
    MyAgentDetailSkillResponse(
        level = 12,
        skillType = 0,
        items =
            listOf(
                MyAgentDetailSkillItemResponse(
                    title = "普通攻擊：不許動！",
                    text = "交替使用體術、手槍和以太鹿彈，向前方進行至多五段的攻擊，造成物理傷害和以太傷害。"
                )
            )
    )
