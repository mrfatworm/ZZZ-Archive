/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.model

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class OfficialNewsListItemTest {

    // Try mockk on androidUnitTest but fail
    @Ignore
    @Test
    fun `test getImageUrl returns correct URL with mock JSONObject`() {
        val newsListItem = stubNewsListItem
        val imageUrl = newsListItem.getImageUrl()
        val expectedUrl = "https://fastcdn.hoyoverse.com/content-v2/nap/126022/93934296a401f3337f65e4fd938ea7e4_7828096096202056509.jpg"
        assertEquals(imageUrl, expectedUrl)
    }


    @Test
    fun getDate() {
        val officialNewsListItem = stubNewsListItem
        val date = officialNewsListItem.getDate()
        assertEquals(date, "2024-09-21")
    }
}
