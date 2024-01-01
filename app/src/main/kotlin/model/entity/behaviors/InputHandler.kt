package model.entity.behaviors

import controller.GameContext
import controller.messages.Move
import model.entity.GameEntity
import model.entity.attributes.Directions
import model.entity.attributes.direction
import model.entity.attributes.position
import model.entity.attributes.targetPosition
import model.entity.types.Creature
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

/*
This is a Behavior class that provides
entity with acting according to user Input
 */

class InputHandler : BaseBehavior() {

    override fun update(entity: GameEntity<Creature>, context: GameContext): Boolean {
        val uiEvent = context.uiEvent
        val player = context.player
        val world = context.world
        if (player == null || uiEvent == null) {
            return true
        }
        val currentPos = player.position
        if (uiEvent is KeyboardEvent) {
            var newPosition = currentPos
            val targetPosition = player.targetPosition
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
                    world.performHit(targetPosition, player, context)
                }
                else -> {
                    print(uiEvent.code)
                    println()
                }
            }
            handleEffects(player, Move(player, newPosition, context))
        } else {
            //TODO add mouse behaviors if needed
        }
        return true
    }
}