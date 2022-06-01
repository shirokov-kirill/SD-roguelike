package model.entity.behaviors

import controller.messages.Clone
import controller.messages.GameMessage
import controller.messages.Move
import controller.world.generator.sameLevelNeighborsShuffled
import model.entity.GameEntity
import model.entity.attributes.effects
import model.entity.attributes.effects.Cloneable
import model.entity.attributes.effects.Embarrassment
import model.entity.attributes.position
import model.entity.types.Creature

abstract class BaseBehavior : Behavior {

    override fun handleEffects(entity: GameEntity<out Creature>, message: GameMessage) {
        val effects = entity.effects
        if (message is Move) {
            for (effect in effects) {
                if (effect is Embarrassment) {
                    message.position = entity.position.sameLevelNeighborsShuffled().first()
                    entity.receiveMessage(message)
                    return
                } else {
                    if (effect is Cloneable) {
                        var needToClone = false
                        if (effect.capacity == effect.actual + 1) {
                            needToClone = true
                        }
                        effect.updateValue()
                        if (needToClone) {
                            entity.receiveMessage(Clone(message.entity, message.context))
                            entity.receiveMessage(message)
                        }
                    }
                }
            }
        }
        entity.receiveMessage(message)
    }

}