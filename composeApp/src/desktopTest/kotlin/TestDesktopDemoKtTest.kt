import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class TestDesktopDemoKtTest {

    @Test
    fun selectAiGender() {
        val result = selectAiGenderDesktop(0)
        assertThat(result).isEqualTo("Female")
    }
}