package model.entity.facets

import controller.messages.Consumed
import controller.messages.Hit
import controller.messages.Response
import model.entity.attributes.hitPoints

class Hitable: BaseFacet<Hit>(Hit::class) {

    override fun receive(message: Hit): Response {
        val entity = message.entity
        val damage = message.damage
        val context = message.context
        entity.hitPoints -= damage
        if(entity.hitPoints <= 0){
            context.world.removeEntity(entity)
        }
        return Consumed
    }

}