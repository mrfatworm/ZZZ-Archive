package feature.hoyolab.data.repository

import feature.hoyolab.model.my_agent_detail.stubMyAgentDetailListItem
import feature.hoyolab.model.stubMyAgentsList
import kotlinx.coroutines.test.runTest
import network.FakeHoYoLabHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HoYoLabAgentRepositoryTest {
    private val httpClient = FakeHoYoLabHttp()
    private val repository = HoYoLabAgentRepositoryImpl(httpClient)

    @Test
    fun `Request agents list THEN success`() = runTest {
        val result = repository.requestPlayerAgentList("", 0, "", "", "")
        assertEquals(Result.success(stubMyAgentsList), result)
    }

    @Test
    fun `Request agents list THEN failure`() = runTest {
        httpClient.setError(true)
        val result = repository.requestPlayerAgentList("", 0, "", "", "").getOrNull()
        assertNull(result)
    }

    @Test
    fun `Request agent detail THEN success`() = runTest {
        val result = repository.requestPlayerAgentDetail("", 0, "", "", "", 0)
        assertEquals(Result.success(stubMyAgentDetailListItem), result)
    }

    @Test
    fun `Request agent detail THEN failure`() = runTest {
        httpClient.setError(true)
        val result = repository.requestPlayerAgentDetail("", 0, "", "", "", 0).getOrNull()
        assertNull(result)
    }
}