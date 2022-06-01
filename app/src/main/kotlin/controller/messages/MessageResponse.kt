package controller.messages

/*
Response is the base interface
for all kinds of Responses in the game
 */

interface Response {

}

/*
Consumed == "Positive" response
*/

object Consumed : Response {
    fun equals(response: Response): Boolean {
        return response == Consumed
    }
}

/*
Pass == "Negative" response
*/

object Pass : Response {
    fun equals(response: Response): Boolean {
        return response == Pass
    }
}

/*
MessageResponse is a Response-implementation that provides
an ability to apply Message chain in the project
*/

data class MessageResponse(private val message: GameMessage) : Response {
    fun process(): Response {
        return message.entity.receiveMessage(message)
    }
}