package model.entity.behaviors

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.BaseType

interface Behavior<T: BaseType> {

    suspend fun update(entity: GameEntity<T>, context: GameContext): Boolean

}