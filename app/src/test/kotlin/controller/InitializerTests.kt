package controller

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InitializerTests {

    @Test
    fun testInitializeForValidStateModificationHandler(){
        val stateModificationsHandler = Initializer.initialize()
        assertEquals(false, stateModificationsHandler == null)
        assertEquals(false, stateModificationsHandler.getAdditionalInfo() == null)
        assertEquals(false, stateModificationsHandler.getGame() == null)
    }

    @Test
    fun testInitializeForNotImplementedError(){
        try{
            val stateModificationsHandler = Initializer.initialize(GameWorldBuilder.LOAD, "")
            assert(false)
        } catch (e: NotImplementedError){
            return
        }
        assert(false)
    }
}