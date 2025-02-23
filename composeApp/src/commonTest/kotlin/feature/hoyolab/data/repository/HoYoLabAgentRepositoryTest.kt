package feature.hoyolab.data.repository

import feature.hoyolab.model.stubMyAgentsList
import kotlinx.coroutines.test.runTest
import network.FakeHoYoLabHttp
import kotlin.test.Test
import kotlin.test.assertEquals

class HoYoLabAgentRepositoryTest {
    private val httpClient = FakeHoYoLabHttp()
    private val repository = HoYoLabAgentRepositoryImpl(httpClient)

    @Test
    fun `Request agents list THEN success`() = runTest {
        val result = repository.requestPlayerAgentList("", 0, "", "", "")
        assertEquals(Result.success(stubMyAgentsList), result)
    }
}