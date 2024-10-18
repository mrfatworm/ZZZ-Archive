/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Ignore
import kotlin.test.Test

class OfficialNewsListItemTest {

    // Try mockk on androidUnitTest but fail
    @Ignore
    @Test
    fun `test getImageUrl returns correct URL with mock JSONObject`() {
        val newsListItem = stubNewsListItem
        val imageUrl = newsListItem.getImageUrl()
        val expectedUrl = "https://fastcdn.hoyoverse.com/content-v2/nap/126022/93934296a401f3337f65e4fd938ea7e4_7828096096202056509.jpg"
        assertThat(imageUrl).isEqualTo(expectedUrl)
    }


    @Test
    fun getDate() {
        val officialNewsListItem = stubNewsListItem
        val date = officialNewsListItem.getDate()
        assertThat(date).isEqualTo("2024-09-21")
    }
}
