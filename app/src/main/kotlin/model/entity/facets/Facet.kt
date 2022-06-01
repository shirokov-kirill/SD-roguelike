package model.entity.facets

import controller.messages.GameMessage
import controller.messages.Response

/*
This is a base interface for any facet
 */

interface Facet<Message>{

    /*
    receive(message) performs an action on entity
     */

    fun receive(message: Message): Response

    /*
    tryReceive(message) tries to apply an action on an entity
    with facets (by message::class)
     */

    fun tryReceive(message: GameMessage): Response

}