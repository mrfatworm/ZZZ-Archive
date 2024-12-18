package feature.forum.domain

import feature.forum.data.FakeForumRepository
import feature.forum.model.stubAllForumState
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ForumUseCaseTest {
    private val repository = FakeForumRepository()
    private val useCase = ForumUseCase(repository)

    @Test
    fun `Get all forum list`() = runTest {
        val result = useCase.getAllForumList().getOrNull()
        assertEquals(stubAllForumState, result)
    }

    @Test
    fun `Get all forum list fail`() = runTest {
        repository.setError(true)
        val result = useCase.getAllForumList().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get all forum list periodically success`() = runTest {
        val result = useCase.getAllForumListPeriodically(10).firstOrNull()
        assertEquals(stubAllForumState, result)
    }
}