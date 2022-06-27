package controller.world.generator

import GameConfig
import controller.Difficulty
import model.entity.GameEntity
import model.entity.factory.*
import model.entity.types.Player
import model.view.state.GameWorld
import model.view.state.resourses.GameBlock
import model.view.state.resourses.GameBlockFactory
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import java.io.File

class GameWorldBuilder {
    private var worldSize: Size3D = GameConfig.WORLD_SIZE
    private var loadingType = GameBuilderType.GENERATE
    private var filePath: String? = GameConfig.GAMEWORLD_BUILDER_FILE
    private var blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()
    private var equipmentFactory: EntityFactory = EquipmentFactory()
    private var mobsFactory: MobsFactory = AncientMobsFactory()
    private var difficulty: Difficulty = Difficulty.MEDIUM
    private var player: GameEntity<Player> = PlayerFactory().createEntity()

    /**
     * passLoadingType(type) method can be used to pass correct
     * loading type property to the Builder
     */

    fun passLoadingType(type: GameBuilderType): GameWorldBuilder {
        loadingType = type
        return this
    }

    /**
     * passFilePath(path) method can be used to pass correct
     * path loading property to the Builder
     */
    fun passFilePath(path: String): GameWorldBuilder {
        filePath = path
        return this
    }

    /**
     * passDifficulty(difficulty) method can be used to pass correct
     * difficulty generator property to the Builder
     */

    fun passDifficulty(difficulty: Difficulty): GameWorldBuilder {
        this.difficulty = difficulty
        return this
    }

    fun withMobsFactory(mobsFactory: MobsFactory): GameWorldBuilder {
        this.mobsFactory = mobsFactory
        return this
    }

    fun withEquipmentFactory(equipmentFactory: EquipmentFactory): GameWorldBuilder {
        this.equipmentFactory = equipmentFactory
        return this
    }

    /**
     * makeCaves() is a private method that can be used to
     * generate final map for the game
     *
     * File format for map is UTF-8 spaces for all floors, anything else for walls
     */
    private fun makeCaves(): GameWorldBuilder {
        when (loadingType) {
            GameBuilderType.GENERATE -> return randomizeTiles().smooth(8)
            GameBuilderType.LOAD -> {
                val file = File(filePath)
                val lines = file.readLines()
                val width = lines.first().length
                val height = lines.size
                assert(!lines.isEmpty() && lines.all { it.length == width }) { "world has to be a rectangle" }

                worldSize = Size3D.create(width, height, 1)
                lines.forEachIndexed { i, line ->
                    line.forEachIndexed { j, char ->
                        val position = Position3D.create(j, i, 0)
                        blocks[position] = when (char) {
                            ' ' -> GameBlockFactory.floor
                            else -> GameBlockFactory.wall
                        }
                    }
                }
                return this
            }
            else -> {
                throw NotImplementedError("Not implemented")
            }
        }
    }

    private fun generateMobs(world: GameWorld) {
        val monstersCount = when (difficulty) {
            Difficulty.EASY -> 10
            Difficulty.MEDIUM -> 15
            Difficulty.HARD -> 20
            Difficulty.EXTREME -> 35
        }
        repeat(monstersCount) {
            val monster = mobsFactory.createEntity()
            world.addEntity(monster, true, GameConfig.WORLD_SIZE)
        }
    }

    private fun generateEquipment(world: GameWorld) {
        val equipmentCount = 15
        repeat(equipmentCount) {
            val equipment = equipmentFactory.createEntity()
            world.addEntity(equipment, true, GameConfig.WORLD_SIZE)
        }
    }

    private fun generatePlayer(world: GameWorld) {
        world.addEntity(player, true)
    }

    /*
    * build() method can be used to
    * create GameWorld object out of GameWorldBuilder
    * with .proceed() method applied before
    * (or you'll get empty map in the game)
    */

    private fun buildInternal(visibleSize: Size3D) = GameWorld(blocks, visibleSize, worldSize)

    fun build(visibleSize: Size3D): GameWorldToPlayerBinding {
        val world = this.makeCaves().buildInternal(visibleSize)
        generateMobs(world)
        generateEquipment(world)
        generatePlayer(world)
        return GameWorldToPlayerBinding(world, player)
    }

    /**
     * randomizeTiles() creates random field
     */
    private fun randomizeTiles(): GameWorldBuilder {
        forAllPositions { pos ->
            blocks[pos] = if (Math.random() < 0.5) {
                GameBlockFactory.floor
            } else GameBlockFactory.wall
        }
        return this
    }

    /**
     * smooth(iterations) makes randomly created map more consistent
     * with the game context
     */

    private fun smooth(iterations: Int): GameWorldBuilder {
        val newBlocks = mutableMapOf<Position3D, GameBlock>()
        repeat(iterations) {
            forAllPositions { pos ->
                val (x, y, z) = pos
                var floors = 0
                var rocks = 0
                pos.sameLevelNeighborsShuffled().plus(pos).forEach { neighbor ->
                    blocks.whenPresent(neighbor) { block ->
                        if (block.isFloor) {
                            floors++
                        } else rocks++
                    }
                }
                newBlocks[Position3D.create(x, y, z)] =
                    if (floors >= rocks) GameBlockFactory.floor else GameBlockFactory.wall
            }
            blocks = newBlocks
        }
        return this
    }

    /**
     * forAllPositions(function) applies a function for each field
     * on the map
     */

    private fun forAllPositions(fn: (Position3D) -> Unit) {
        worldSize.fetchPositions().forEach(fn)
    }

    private fun MutableMap<Position3D, GameBlock>.whenPresent(pos: Position3D, fn: (GameBlock) -> Unit) {
        this[pos]?.let(fn)
    }
    enum class GameBuilderType {
        GENERATE, LOAD
    }
}