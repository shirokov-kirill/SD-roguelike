package model.entity.behaviors

import controller.GameContext
import controller.messages.Move
import controller.world.generator.sameLevelNeighborsShuffled
import model.entity.GameEntity
import model.entity.attributes.position
import model.entity.types.Creature
import org.hexworks.zircon.api.data.Position3D

class AggressiveMove : MonsterMove() {

    override fun update(entity: GameEntity<Creature>, context: GameContext): Boolean {
        val player = context.player
        val newPosition: Position3D
        if (player == null) {
            newPosition = entity.position.sameLevelNeighborsShuffled().first() //random walk
        } else {
            val playerPosition = player.position
            val dx = entity.position.x - playerPosition.x
            val dy = entity.position.y - playerPosition.y
            if (dx < 0) {
                newPosition = if (dy < 0) {
                    if (Math.random() < 0.5) {
                        entity.position.withRelativeX(1)
                    } else {
                        entity.position.withRelativeY(1)
                    }
                } else {
                    if (Math.random() < 0.5) {
                        entity.position.withRelativeX(1)
                    } else {
                        entity.position.withRelativeY(-1)
                    }
                }
            } else {
                newPosition = if (dy < 0) {
                    if (Math.random() < 0.5) {
                        entity.position.withRelativeX(-1)
                    } else {
                        entity.position.withRelativeY(1)
                    }
                } else {
                    if (Math.random() < 0.5) {
                        entity.position.withRelativeX(-1)
                    } else {
                        entity.position.withRelativeY(-1)
                    }
                }
            }
        }
        handleEffects(entity, Move(entity, newPosition, context))
        return true
    }

}