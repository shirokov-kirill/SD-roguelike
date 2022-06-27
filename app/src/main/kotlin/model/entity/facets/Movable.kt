package model.entity.facets

import controller.messages.*
import model.entity.attributes.position
import model.entity.types.Player

/*
Movable is a facet that symbolise
that entity can move on the map
 */

class Movable : BaseFacet<Move>(Move::class) {

    override fun receive(message: Move): Response {
        val entity = message.entity
        val position = message.position
        val world = message.context.world
        val prevPosition = entity.position
        var result: Response = Pass
        if (world.moveEntity(entity, position)) {
            result = if (entity.type == Player) {
                MessageResponse(
                    MoveView(
                        message.context,
                        entity,
                        prevPosition
                    )
                ).process()
            } else {
                Consumed
            }
        }
        return result
    }

}