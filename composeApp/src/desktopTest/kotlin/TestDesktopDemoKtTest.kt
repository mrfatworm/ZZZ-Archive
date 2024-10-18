import kotlin.test.Test
import kotlin.test.assertEquals

class TestDesktopDemoKtTest {

    @Test
    fun selectAiGender() {
        val result = selectAiGenderDesktop(0)
        assertEquals(result, "Female")
    }
}