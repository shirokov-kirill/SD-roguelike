package controller

import controller.world.generator.GameWorldBuilder
import org.hexworks.zircon.api.data.Size3D
import org.junit.jupiter.api.Test

class InitializerTests {

    @Test
    fun testInitializeForValidStateModificationHandler() {
        Initializer.initialize(difficulty = Difficulty.EASY)
    }

    @Test
    fun testInitializeLoad() {
        Initializer.initialize(
            GameWorldBuilder.GameBuilderType.LOAD,
            "src/test/resources/testMap.txt",
            Difficulty.EASY,
            Size3D.create(3, 2, 1)
        )
    }
}