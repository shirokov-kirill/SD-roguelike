package controller

import model.view.state.resourses.GameBlock
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import model.view.state.resourses.GameBlockFactory
import model.view.state.GameWorld

class GameWorldBuilder(private val worldSize: Size3D) {

    private var loadingType = GENERATE
    private var filePath = DEFAULT_FILEPATH
    private val width = worldSize.xLength
    private val height = worldSize.yLength
    private var blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()

    fun passLoadingType(type: String, path: String): GameWorldBuilder {
        loadingType = type
        filePath = path
        return this
    }

    private fun makeCaves(): GameWorldBuilder {
        return randomizeTiles()
            .smooth(8)
    }

    fun proceed(): GameWorldBuilder{
        if(loadingType == GENERATE){
            return makeCaves()
        } else {
            //TODO
            return this
        }
    }

    fun build(visibleSize: Size3D): GameWorld = GameWorld(blocks, visibleSize, worldSize)

    private fun randomizeTiles(): GameWorldBuilder {
        forAllPositions { pos ->
            blocks[pos] = if (Math.random() < 0.5) {
                GameBlockFactory.floor()
            } else GameBlockFactory.wall()
        }
        return this
    }

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

    private fun forAllPositions(fn: (Position3D) -> Unit) {
        worldSize.fetchPositions().forEach(fn)
    }

    private fun MutableMap<Position3D, GameBlock>.whenPresent(pos: Position3D, fn: (GameBlock) -> Unit) {
        this[pos]?.let(fn)
    }

    companion object {

        const val GENERATE = "GENERATE"
        const val LOAD = "LOAD"
        const val DEFAULT_FILEPATH = ""

    }
}