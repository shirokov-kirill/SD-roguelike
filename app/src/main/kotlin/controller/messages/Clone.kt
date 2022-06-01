package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.Creature

data class Clone(
    override val entity: GameEntity<out Creature>,
    override val context: GameContext
) : GameMessage(entity, context)