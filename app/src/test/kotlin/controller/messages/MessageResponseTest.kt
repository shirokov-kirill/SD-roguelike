package controller.messages

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MessageResponseTest {

    @Test
    fun testUnequalPassConsumed() {
        assertNotEquals<Response>(Consumed, Pass)
    }

    @Test
    fun testUnequalConsumedPass() {
        assertNotEquals<Response>(Pass, Consumed)
    }

    @Test
    fun testEqualConsumedConsumed() {
        assertEquals(Consumed, Consumed)
    }

    @Test
    fun testEqualPassPass() {
        assertEquals(Pass, Pass)
    }
}