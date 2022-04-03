package model.entity.facets

import controller.messages.GameMessage
import controller.messages.Response


interface Facet<Message>{

    suspend fun receive(message: Message): Response

    suspend fun tryReceive(message: GameMessage): Response

}