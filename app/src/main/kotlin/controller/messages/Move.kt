package controller.messages

import controller.GameContext
import controller.GameEntity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D

data class Move(
    override val source: GameEntity<EntityType>,
    val position: Position3D,
    override val context: GameContext) : GameMessage