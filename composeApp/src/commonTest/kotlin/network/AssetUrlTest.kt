/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package network

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.agent.data.mapper.toAgentsListItemEntity
import feature.agent.model.AgentListItemResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AssetUrlTest {
    private val repoUrl = Regex("""^https://cdn\.jsdelivr\.net/gh/mrfatworm/ZZZ-Archive-Asset@(dev|main)""")

    @Test
    fun `Asset url points at the asset folder on jsDelivr`() {
        val url = assetUrl("Banner/1.webp")
        assertTrue(Regex(repoUrl.pattern + """/Asset/Banner/1\.webp$""").matches(url), url)
    }

    @Test
    fun `Api url points at the api folder of the same branch`() {
        val branch = repoUrl.find(ZzzConfig.ASSET_URL)!!.groupValues[1]
        assertEquals(
            "https://cdn.jsdelivr.net/gh/mrfatworm/ZZZ-Archive-Asset@$branch/Api",
            ZzzConfig.API_URL
        )
    }

    @Test
    fun `Agent profile image is built through assetUrl`() {
        val entity = AgentListItemResponse(id = 3).toAgentsListItemEntity()
        assertEquals(assetUrl("Agent/Profile/3.webp"), entity.imageUrl)
    }
}
