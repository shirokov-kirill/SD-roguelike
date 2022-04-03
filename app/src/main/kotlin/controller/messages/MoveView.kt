package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.BaseType
import org.hexworks.zircon.api.data.Position3D

data class MoveView(
    override val context: GameContext,
    override val entity: GameEntity<out BaseType>,
    val previousPosition: Position3D) : GameMessage(entity, previousPosition, context)