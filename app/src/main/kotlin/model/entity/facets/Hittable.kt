package model.entity.facets

import controller.messages.Consumed
import controller.messages.Hit
import controller.messages.Response
import model.entity.attributes.*
import model.entity.attributes.effects.Effect
import model.entity.attributes.effects.Embarrassment

class Hittable : BaseFacet<Hit>(Hit::class) {

    fun generateEffects(): Array<Effect>? {
        if (Math.random() > 0.75) {
            return arrayOf(Embarrassment(3))
        }
        return null
    }

    override fun receive(message: Hit): Response {
        val entity = message.entity
        val damage = message.damage
        val context = message.context
        entity.performHit(damage, generateEffects())
        if (entity.hitPoints <= 0) {
            message.fromEntity.experience += LEVEL_EXPERIENCE_TABLE[entity.level][1] //+= maximum enemy hit points
            message.fromEntity.updateStats()
            context.world.removeEntity(entity)
        }
        return Consumed
    }
}