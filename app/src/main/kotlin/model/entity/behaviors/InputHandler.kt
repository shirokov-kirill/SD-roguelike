package model.entity.behaviors

import controller.GameContext
import model.entity.GameEntity
import controller.messages.Move
import model.entity.attributes.Directions
import model.entity.attributes.direction
import model.entity.attributes.position
import model.entity.types.Player
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

/*
This is a Behavior class that provides
entity with acting according to user Input
 */

class InputHandler() : Behavior<Player> {

    override suspend fun update(entity: GameEntity<Player>, context: GameContext): Boolean {
        val uiEvent = context.uiEvent
        val player = context.player
        val world = context.world
        val currentPos = player.position
        if (uiEvent is KeyboardEvent) {
            var newPosition = currentPos
            var targetPosition = currentPos
            when (uiEvent.code) {
                KeyCode.KEY_W -> {
                    player.direction = Directions.TOP
                    newPosition = currentPos.withRelativeY(-1)
                }
                KeyCode.KEY_A -> {
                    player.direction = Directions.LEFT
                    newPosition = currentPos.withRelativeX(-1)
                }
                KeyCode.KEY_S -> {
                    player.direction = Directions.BOTTOM
                    newPosition = currentPos.withRelativeY(1)
                }
                KeyCode.KEY_D -> {
                    player.direction = Directions.RIGHT
                    newPosition = currentPos.withRelativeX(1)
                }
                KeyCode.SPACE -> {
                    when(player.direction) {
                        Directions.LEFT -> {
                            targetPosition = currentPos.withRelativeX(-1)
                        }
                        Directions.RIGHT -> {
                            targetPosition = currentPos.withRelativeX(1)
                        }
                        Directions.TOP -> {
                            targetPosition = currentPos.withRelativeY(-1)
                        }
                        Directions.BOTTOM -> {
                            targetPosition = currentPos.withRelativeY(1)
                        }
                    }
                    world.performHit(targetPosition)
                }
                else -> {
                    print(uiEvent.code)
                    println()
                }
            }
            player.receiveMessage(Move(player, newPosition, context))
        } else {
            //TODO add mouse behaviors if needed
        }
        return true
    }
}