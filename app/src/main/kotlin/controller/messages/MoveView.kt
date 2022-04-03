package controller.messages

import controller.GameContext
import controller.GameEntity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D

data class MoveView(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val previousPosition: Position3D
) : GameMessage