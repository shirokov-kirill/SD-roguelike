package model.entity.behaviors

import controller.GameContext
import model.entity.GameEntity
import model.entity.attributes.recalculateEffects
import model.entity.types.Creature

class EffectsDecreaser : BaseBehavior() {

    override fun update(entity: GameEntity<Creature>, context: GameContext): Boolean {
        entity.recalculateEffects()
        return true
    }

}