package model.entity.facets

import controller.messages.Move
import controller.GameContext
import controller.messages.MoveView
import model.entity.attributes.position
import model.entity.types.Player
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.MessageResponse
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object Movable : BaseFacet<GameContext, Move>(Move::class) {

    override suspend fun receive(message: Move): Response {
        val entity = message.source
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
                ))
            } else {
                result = Consumed
            }
        }
        return result
    }

}