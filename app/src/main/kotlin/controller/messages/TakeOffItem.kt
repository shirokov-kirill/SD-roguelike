package controller.messages

import controller.GameContext
import model.entity.GameEntity
import model.entity.types.Creature
import model.entity.types.Equipment

class TakeOffItem(
    override val entity: GameEntity<out Creature>,
    val equipment: GameEntity<Equipment>,
    override val context: GameContext
) : GameMessage(entity, context)