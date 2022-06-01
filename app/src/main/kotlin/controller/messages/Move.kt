package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.Creature
import org.hexworks.zircon.api.data.Position3D

/*
Move is a specific message that
tells an entity it needs to move
 */

data class Move(
    override val entity: GameEntity<out Creature>,
    var position: Position3D,
    override val context: GameContext
) : GameMessage(entity, context)