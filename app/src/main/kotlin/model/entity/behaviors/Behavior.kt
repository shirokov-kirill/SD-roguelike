package model.entity.behaviors

import controller.GameContext
import controller.messages.GameMessage
import model.entity.GameEntity
import model.entity.types.Creature

/*
This is a base interface for any behavior
 */

interface Behavior {

    /*
    handleEffects(entity, message) applies effects
    that are on entity to message
     */

    fun handleEffects(entity: GameEntity<out Creature>, message: GameMessage)

    /*
    update(entity, context) updates current entity
    according to context
     */

    fun update(entity: GameEntity<Creature>, context: GameContext): Boolean

}