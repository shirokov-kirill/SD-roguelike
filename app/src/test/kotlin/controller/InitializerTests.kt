package controller

import org.junit.jupiter.api.Test

class InitializerTests {

    @Test
    fun testInitializeForValidStateModificationHandler() {
        Initializer.initialize(difficulty = Difficulty.EASY)
    }

    @Test
    fun testInitializeForNotImplementedError() {
//        Initializer.initialize(GameWorldBuilder.LOAD, "", Difficulty.EASY)
    }
}