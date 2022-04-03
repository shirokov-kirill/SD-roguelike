package model.entity.facets

import controller.messages.*
import model.entity.attributes.position
import model.entity.types.Creature
import model.entity.types.Player

class Movable() : BaseFacet<Move>(Move::class){

    override suspend fun receive(message: Move): Response {
        val entity = message.entity
        val position = message.position
        val world = message.context.world
        val prevPosition = entity.position
        var result: Response = Pass
        if (world.moveEntity(entity, position)) {
            if(entity.type == Player) {
                result = MessageResponse(MoveView(
                    message.context,
                    entity,
                    prevPosition
                )).process()
            } else {
                result = Consumed
            }
        }
        return result
    }

}