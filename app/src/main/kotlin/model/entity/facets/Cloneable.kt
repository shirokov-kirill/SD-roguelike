package model.entity.facets

import controller.messages.Clone
import controller.messages.Consumed
import controller.messages.Response

class Cloneable : BaseFacet<Clone>(Clone::class) {

    override fun receive(message: Clone): Response {
        val entity = message.entity
        val newEntity = entity.deepCopy()
        message.context.world.addEntity(newEntity, true)
        return Consumed
    }
}