import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class TestAndroidDemoKtTest {

    @Test
    fun testSelectAiGender() {
        val result = selectAiGenderAndroid(0)
        assertThat(result).isEqualTo("Female")
    }

}