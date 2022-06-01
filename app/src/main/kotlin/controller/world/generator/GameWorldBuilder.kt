package controller

import controller.world.generator.GameWorldToPlayerBinding
import model.entity.GameEntity
import model.entity.factory.*
import model.entity.types.Player
import model.view.state.Game
import model.view.state.resourses.GameBlock
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import model.view.state.resourses.GameBlockFactory
import model.view.state.GameWorld

class GameWorldBuilder(private val worldSize: Size3D) {

    private var loadingType = GENERATE
    private var filePath: String? = null
    private var blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()
    private var equipmentFactory: EntityFactory = EquipmentFactory()
    private var mobsFactory: MobsFactory = AncientMobsFactory()
    private var difficulty: Difficulty = Difficulty.MEDIUM
    private var player: GameEntity<Player> = PlayerFactory().createEntity()

    /*
    passLoadingType(type) method can be used to pass correct
    loading type property to the Builder
    */

    fun passLoadingType(type: String): GameWorldBuilder {
        if (type == GENERATE || type == LOAD) {
            loadingType = type
        } else {
            throw IllegalArgumentException("Not supported string passed")
        }
        return this
    }

    /*
    passFilePath(path) method can be used to pass correct
    path loading property to the Builder
    */

    fun passFilePath(path: String): GameWorldBuilder {
        filePath = path
        return this
    }

    /*
    passDifficulty(difficulty) method can be used to pass correct
    difficulty generator property to the Builder
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

    /*
    makeCaves() is a private method that can be used to
    generate final map for the game
    */

    private fun makeCaves(): GameWorldBuilder {
        if (loadingType == GENERATE) {
            return randomizeTiles()
                .smooth(8)
        } else {
            //TODO
            throw NotImplementedError("Not implemented")
        }
    }

    private fun generateMobs(world: GameWorld) {
        if (loadingType == GENERATE) {
            var monstersCount = 0
            when (difficulty) {
                Difficulty.EASY -> monstersCount = 10
                Difficulty.MEDIUM -> monstersCount = 15
                Difficulty.HARD -> monstersCount = 20
                Difficulty.EXTREME -> monstersCount = 35
            }
            while (monstersCount > 0) {
                val monster = mobsFactory.createEntity()
                world.addEntity(monster, true, GameConfig.WORLD_SIZE)
                monstersCount--
            }
        } else {
            return
        }
    }

    private fun generateEquipment(world: GameWorld) {
        if (loadingType == GENERATE) {
            var equipmentCount = 15


            while (equipmentCount > 0) {
                val equipment = equipmentFactory.createEntity()
                world.addEntity(equipment, true, GameConfig.WORLD_SIZE)
                equipmentCount--
            }
        } else {
            return
        }
    }

    private fun generatePlayer(world: GameWorld) {
        if (loadingType == GENERATE) {
            world.addEntity(player, true)
        } else {
            return
        }
    }

    /*
    build() method can be used to
    create GameWorld object out of GameWorldBuilder
    with .proceed() method applied before
    (or you'll get empty map in the game)
    */

    private fun buildInternal(visibleSize: Size3D) = GameWorld(blocks, visibleSize, worldSize)

    fun build(visibleSize: Size3D): GameWorldToPlayerBinding {
        var world = this.makeCaves().buildInternal(visibleSize)
        generateMobs(world)
        generateEquipment(world)
        generatePlayer(world)
        return GameWorldToPlayerBinding(world, player)
    }

    /*
    randomizeTiles() creates random field
    */

    private fun randomizeTiles(): GameWorldBuilder {
        forAllPositions { pos ->
            blocks[pos] = if (Math.random() < 0.5) {
                GameBlockFactory.floor()
            } else GameBlockFactory.wall()
        }
        return this
    }

    /*
    smooth(iterations) makes randomly created map more consistent
    with the game context
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
                    if (floors >= rocks) GameBlockFactory.floor() else GameBlockFactory.wall()
            }
            blocks = newBlocks
        }
        return this
    }

    /*
    firAllPositions(function) applies a function for each field
    on the map
     */

    private fun forAllPositions(fn: (Position3D) -> Unit) {
        worldSize.fetchPositions().forEach(fn)
    }

    private fun MutableMap<Position3D, GameBlock>.whenPresent(pos: Position3D, fn: (GameBlock) -> Unit) {
        this[pos]?.let(fn)
    }

    /*
    companion object holds some constants
    associated only with GameWorldBuilder
     */

    companion object {

        const val GENERATE = "GENERATE"
        const val LOAD = "LOAD"

    }
}