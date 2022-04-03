package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.BaseType
import org.hexworks.zircon.api.data.Position3D

open class GameMessage(
    open val entity: GameEntity<out BaseType>,
    open val position: Position3D,
    open val context: GameContext
)