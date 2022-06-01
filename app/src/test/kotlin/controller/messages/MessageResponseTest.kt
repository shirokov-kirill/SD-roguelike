package controller.messages

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MessageResponseTest {

    @Test
    fun testUnequalPassConsumed() {
        assertEquals(false, Pass.equals(Consumed))
    }

    @Test
    fun testUnequalConsumedPass() {
        assertEquals(false, Consumed.equals(Pass))
    }

    @Test
    fun testEqualConsumedConsumed() {
        assertEquals(true, Consumed.equals(Consumed))
    }

    @Test
    fun testEqualPassPass() {
        assertEquals(true, Pass.equals(Pass))
    }
}