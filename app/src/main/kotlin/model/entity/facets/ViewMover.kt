package model.entity.facets

import controller.messages.Consumed
import controller.messages.MoveView
import controller.messages.Response
import model.entity.attributes.position

/*
Movable is a facet that symbolise
that entity can move visible map frame
 */

class ViewMover : BaseFacet<MoveView>(MoveView::class){

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