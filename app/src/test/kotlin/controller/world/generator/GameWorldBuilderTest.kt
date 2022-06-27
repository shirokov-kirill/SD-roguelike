package controller.world.generator

import GameConfig
import org.hexworks.zircon.api.data.Position3D
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameWorldBuilderTest {

    private val gameWorldBuilder = GameWorldBuilder()

    @Test
    fun createNotNullableGameWorldMapTest() {
        val (worldMap, _) = gameWorldBuilder.passLoadingType(GameWorldBuilder.GameBuilderType.GENERATE)
            .build(GameConfig.GAME_AREA_SIZE)
        assertEquals(true, worldMap.hasBlockAt(Position3D.create(0, 0, 0)))
    }

    @Test
    fun generateGameWorldMapTest() {
        val (worldMap, _) = gameWorldBuilder.passLoadingType(GameWorldBuilder.GameBuilderType.GENERATE)
            .build(GameConfig.GAME_AREA_SIZE)
        assertEquals(true, worldMap.hasBlockAt(Position3D.create(0, 0, 0)))
    }

    @Test
    fun loadGameWorldMapTest() {
        gameWorldBuilder.passLoadingType(GameWorldBuilder.GameBuilderType.LOAD)
            .build(GameConfig.GAME_AREA_SIZE)
    }
}