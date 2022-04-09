package model.entity.behaviors

import controller.messages.GameMessage
import controller.messages.Move
import controller.sameLevelNeighborsShuffled
import model.entity.GameEntity
import model.entity.attributes.effects
import model.entity.attributes.effects.Embarrassment
import model.entity.attributes.position
import model.entity.types.Creature

abstract class BaseBehavior: Behavior {

    override fun handleEffects(entity: GameEntity<out Creature>, message: GameMessage) {
        val effects = entity.effects
        if(message is Move){
            for (effect in effects){
                if(effect is Embarrassment){
                    message.position = entity.position.sameLevelNeighborsShuffled().first()
                    entity.receiveMessage(message)
                    return
                }
            }
        }
        entity.receiveMessage(message)
    }

}