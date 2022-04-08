package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.Creature

/*
GameMessage is the base class for any Message in the game.
Messages allow entities to communicate with each other
 */

open class GameMessage(
    open val entity: GameEntity<out Creature>,
    open val context: GameContext
)