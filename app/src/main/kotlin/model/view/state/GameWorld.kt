package model.view.state

import controller.GameContext
import model.entity.GameEntity
import model.entity.attributes.position
import model.entity.types.BaseType
import model.entity.types.Player
import model.view.state.resourses.GameBlock
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.GameArea
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

class GameWorld(
    blocks: Map<Position3D, GameBlock>,
    visibleSize: Size3D,
    actualSize: Size3D,
) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
    .withVisibleSize(visibleSize)
    .withActualSize(actualSize)
    .build() {

    val engine = GameEngine()

    init {
        blocks.forEach { (pos, block) ->
            setBlockAt(pos, block)
            block.entity.position = pos
            engine.addEntity(block.entity)
        }
    }

    fun update(screen: Screen, event: UIEvent, player: GameEntity<Player>) {
        engine.executeTurn(GameContext(this, screen, event, player))
    }

    fun moveEntity(entity: GameEntity<out BaseType>, position: Position3D): Boolean {
        var success = false

        val old = fetchBlockAt(entity.position)
        val new = fetchBlockAt(position)

        if (old.isPresent && new.isPresent && new.get().isEmptyBlock) {
            success = true
            old.get().removeEntity()
            new.get().addEntity(entity)
            entity.position = position
        }
        return success
    }

    fun performHit(position: Position3D) {
        val target = fetchBlockAt(position)
        if(target.isPresent) {
            target.get().hit()
        }
    }

    fun addEntity(entity: GameEntity<out BaseType>, withGuarantee: Boolean, mapSize: Size3D = visibleSize): Boolean{
        var attemptsCount: Int
        if(withGuarantee){
            attemptsCount = -1
        } else {
            attemptsCount = 4
        }
        var res = Maybe.empty<Boolean>()
        var position: Position3D = Position3D.unknown()
        while (attemptsCount != 0 && !res.isPresent){

            position = Position3D.create(
                (Math.random() * mapSize.xLength).toInt(),
                (Math.random() * mapSize.yLength).toInt(),
                0
            )

            fetchBlockAt(position).map {
                if(it.isEmptyBlock){
                    res = Maybe.of(it.isEmptyBlock)
                }
            }
            attemptsCount--
        }
        if(res.isPresent && res.get()){
            entity.position = position
            engine.addEntity(entity)
            fetchBlockAt(position).map {
                it.addEntity(entity)
            }
            return true
        }
        return false
    }
}