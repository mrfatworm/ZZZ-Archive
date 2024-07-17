import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class TestIosDemoKtTest {

    @Test
    fun testSelectAiGender() {
        val result = selectAiGenderIos(0)
        assertThat(result).isEqualTo("Female")
    }
}