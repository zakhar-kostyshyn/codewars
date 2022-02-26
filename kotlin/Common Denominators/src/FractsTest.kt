import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FractsTest {

    @Test
    fun basicTests() {

        var lst = arrayOf(longArrayOf(1, 2), longArrayOf(1, 3), longArrayOf(1, 4))
        testing(lst, "(6,12)(4,12)(3,12)")


        lst = arrayOf()
        testing(lst, "")

//        lst = arrayOf(longArrayOf(69, 130), longArrayOf(87, 1310), longArrayOf(30, 40))
//        testing(lst, "(18078,34060)(2262,34060)(25545,34060)")

    }

    private fun testing(lst: Array<LongArray>, expected: String) {
        val actual = Fracts.convertFrac(lst)
        assertEquals(expected, actual)
    }

}
