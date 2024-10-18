import kotlin.test.Test
import kotlin.test.assertEquals

class TestIosDemoKtTest {

    @Test
    fun testSelectAiGender() {
        val result = selectAiGenderIos(0)
        assertEquals(result, "Female")
    }
}