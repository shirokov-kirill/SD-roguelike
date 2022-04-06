package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.BaseType
import org.hexworks.zircon.api.data.Position3D

/*
GameMessage is the base class for any Message in the game.
Messages allow entities to communicate with each other
 */

open class GameMessage(
    open val entity: GameEntity<out BaseType>,
    open val context: GameContext
)