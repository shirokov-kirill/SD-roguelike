package controller.messages

interface Response {

}

object Consumed : Response {
    fun equals(response: Response): Boolean {
        return response == Consumed
    }
}

object Pass : Response {
    fun equals(response: Response): Boolean {
        return response == Pass
    }
}

data class MessageResponse(private val message: GameMessage) : Response {
    suspend fun process(): Response{
        return message.entity.receiveMessage(message)
    }
}