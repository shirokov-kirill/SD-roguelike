package model.entity.behaviors

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.BaseType

/*
This is a base interface for any behavior
 */

interface Behavior<T: BaseType> {

    /*
    update(entity, context) updates current entity
    according to context
     */

    fun update(entity: GameEntity<T>, context: GameContext): Boolean

}