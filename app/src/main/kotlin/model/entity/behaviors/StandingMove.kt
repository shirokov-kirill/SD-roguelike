package model.entity.behaviors

import controller.GameContext
import model.entity.GameEntity
import model.entity.attributes.position
import model.entity.types.Creature

class StandingMove: MonsterMove() {

    override fun update(entity: GameEntity<Creature>, context: GameContext): Boolean {
        val newPosition = entity.position
        context.world.moveEntity(entity, newPosition)
        return true
    }

}