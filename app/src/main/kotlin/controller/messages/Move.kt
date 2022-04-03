package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.BaseType
import org.hexworks.zircon.api.data.Position3D

data class Move(
    override val entity: GameEntity<out BaseType>,
    override val position: Position3D,
    override val context: GameContext) : GameMessage(entity, position, context)