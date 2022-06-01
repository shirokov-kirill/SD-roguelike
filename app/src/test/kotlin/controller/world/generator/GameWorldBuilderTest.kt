package controller.world.generator

import controller.GameWorldBuilder
import org.hexworks.zircon.api.data.Position3D
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameWorldBuilderTest {

    private val gameWorldBuilder = GameWorldBuilder(GameConfig.WORLD_SIZE)

    @Test
    fun createNotNullableGameWorldMapTest() {
        val (worldMap, player) = gameWorldBuilder.passLoadingType(GameWorldBuilder.GENERATE)
            .build(GameConfig.GAME_AREA_SIZE)
        assertEquals(true, worldMap.hasBlockAt(Position3D.create(0, 0, 0)))
    }

    @Test
    fun generateGameWorldMapTest() {
        val (worldMap, player) = gameWorldBuilder.passLoadingType(GameWorldBuilder.GENERATE)
            .build(GameConfig.GAME_AREA_SIZE)
        assertEquals(true, worldMap.hasBlockAt(Position3D.create(0, 0, 0)))
    }

    @Test
    fun loadNotImplementedErrorGameWorldMapTest() {
        try {
            val (worldMap, player) = gameWorldBuilder.passLoadingType(GameWorldBuilder.LOAD)
                .build(GameConfig.GAME_AREA_SIZE)
            assert(false)
        } catch (e: NotImplementedError) {
            return
        }
        assert(false)
    }
}