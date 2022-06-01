package model.entity.behaviors

import controller.GameContext
import model.entity.GameEntity
import model.entity.attributes.hitPoints
import model.entity.attributes.maxHitPoints
import model.entity.types.Creature

class MutableBehavior : BaseBehavior() {

    private var behavior: MonsterMove = AggressiveMove()

    private val requireForAggressive = 0.80
    private val disorientedBefore = 0.10

    override fun update(entity: GameEntity<Creature>, context: GameContext): Boolean {
        behavior = if (entity.hitPoints >= entity.maxHitPoints * requireForAggressive) {
            AggressiveMove()
        } else if (entity.hitPoints < entity.maxHitPoints * requireForAggressive &&
            entity.hitPoints >= entity.maxHitPoints * disorientedBefore
        ) {
            ScaryMove()
        } else {
            StandingMove()
        }
        return behavior.update(entity, context)
    }

}