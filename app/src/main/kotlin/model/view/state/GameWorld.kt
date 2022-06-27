package model.view.state

import controller.GameContext
import model.entity.GameEntity
import model.entity.attributes.addItemToInventory
import model.entity.attributes.position
import model.entity.types.BaseType
import model.entity.types.Creature
import model.entity.types.Equipment
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
import java.awt.geom.Point2D.distance

class GameWorld(
    blocks: Map<Position3D, GameBlock>,
    visibleSize: Size3D,
    actualSize: Size3D,
) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
    .withVisibleSize(visibleSize)
    .withActualSize(actualSize)
    .build() {

    private val engine = GameEngine(this)

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

    fun moveEntity(entity: GameEntity<out Creature>, position: Position3D): Boolean {
        var success = false

        val old = fetchBlockAt(entity.position)
        val new = fetchBlockAt(position)

        if (old.isPresent && new.isPresent) {
            if (new.get().isEmptyBlock) {
                success = true
                old.get().removeEntity()
                new.get().addEntity(entity)
                entity.position = position
            } else if (new.get().isEquipmentEntity) {
                success = true
                entity.addItemToInventory(new.get().entity as GameEntity<Equipment>)
                old.get().removeEntity()
                new.get().addEntity(entity)
                entity.position = position
            }
        }
        return success
    }

    fun performHit(position: Position3D, fromEntity: GameEntity<out Creature>, context: GameContext) {
        val target = fetchBlockAt(position)
        val fromPos = fromEntity.position
        if (target.isPresent && areNeighbors(fromPos, position)) {
            target.get().hit(fromEntity, context)
        }
    }

    private fun areNeighbors(
        fromPos: Position3D,
        position: Position3D
    ) = distance(fromPos.x.toDouble(), fromPos.y.toDouble(), position.x.toDouble(), position.y.toDouble()) == 1.0

    fun getCreatureOnPosition(position: Position3D): GameEntity<Creature>? {
        val gameEntity = fetchBlockAt(position).map {
            if (it.isEmptyBlock) {
                return@map null
            } else {
                if (it.entity.isCreature()) {
                    return@map it.entity
                } else {
                    return@map null
                }
            }
        }
        if (gameEntity.isPresent) {
            return gameEntity.get() as GameEntity<Creature>
        }
        return null
    }

    fun addEntity(entity: GameEntity<out BaseType>, withGuarantee: Boolean, mapSize: Size3D = visibleSize): Boolean {
        var attemptsCount: Int = if (withGuarantee) {
            -1
        } else {
            4
        }
        var res = Maybe.empty<Boolean>()
        var position: Position3D = Position3D.unknown()
        while (attemptsCount != 0 && !res.isPresent) {

            position = Position3D.create(
                (Math.random() * mapSize.xLength).toInt(),
                (Math.random() * mapSize.yLength).toInt(),
                0
            )

            fetchBlockAt(position).map {
                if (it.isEmptyBlock) {
                    res = Maybe.of(it.isEmptyBlock)
                }
            }
            attemptsCount--
        }
        if (res.isPresent && res.get()) {
            entity.position = position
            engine.addEntity(entity)
            fetchBlockAt(position).map {
                it.addEntity(entity)
            }
            return true
        }
        return false
    }

    fun removeEntity(entity: GameEntity<out BaseType>) {

        fetchBlockAt(entity.position).map {
            it.removeEntity()
        }
        engine.removeEntity(entity)
    }
}