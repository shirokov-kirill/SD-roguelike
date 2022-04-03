package model.entity.facets

import controller.GameContext
import controller.messages.MoveView
import model.entity.attributes.position
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object ViewMover : BaseFacet<GameContext, MoveView>(MoveView::class) {

    override suspend fun receive(message: MoveView): Response {
        val (context, source, previousPosition) = message
        val world = context.world
        val screenPos = source.position - world.visibleOffset
        val halfHeight = world.visibleSize.yLength / 2
        val halfWidth = world.visibleSize.xLength / 2
        val currentPosition = source.position
        when {
            previousPosition.y > currentPosition.y && screenPos.y < halfHeight -> {
                world.scrollOneBackward()
            }
            previousPosition.y < currentPosition.y && screenPos.y > halfHeight -> {
                world.scrollOneForward()
            }
            previousPosition.x > currentPosition.x && screenPos.x < halfWidth -> {
                world.scrollOneLeft()
            }
            previousPosition.x < currentPosition.x && screenPos.x > halfWidth -> {
                world.scrollOneRight()
            }
        }
        return Consumed
    }
}