package model.entity.facets

import controller.messages.GameMessage
import controller.messages.Pass
import controller.messages.Response
import kotlin.reflect.KClass

/*
BaseFacet is a base class for any Facet
 */

abstract class BaseFacet<P : GameMessage>(
    private val messageType: KClass<P>
) : Facet<P> {

    @Suppress("UNCHECKED_CAST")
    override fun tryReceive(message: GameMessage): Response {
        return if (message::class == messageType) {
            receive(message as P)
        } else {
            Pass
        }
    }

}