package model.entity.behaviors

import controller.GameContext
import controller.messages.Move
import model.entity.GameEntity
import model.entity.attributes.position
import model.entity.types.Creature

class StandingMove: MonsterMove() {

    override fun update(entity: GameEntity<Creature>, context: GameContext): Boolean {
        val newPosition = entity.position
        handleEffects(entity, Move(entity, newPosition, context))
        return true
    }

}