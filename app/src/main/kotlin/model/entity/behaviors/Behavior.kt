package model.entity.behaviors

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.Creature

/*
This is a base interface for any behavior
 */

interface Behavior{

    /*
    update(entity, context) updates current entity
    according to context
     */

    fun update(entity: GameEntity<Creature>, context: GameContext): Boolean

}