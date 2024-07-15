import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class TestDemoTest {

    @Test
    fun testSelectAiGender() {
        val result = selectAiGender(0)
        assertThat(result).isEqualTo("Female")
    }

}