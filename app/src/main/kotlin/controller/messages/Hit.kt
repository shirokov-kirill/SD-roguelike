package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.Creature


data class Hit(
    override val entity: GameEntity<out Creature>,
    val damage: Int,
    override val context: GameContext
) : GameMessage(entity, context)