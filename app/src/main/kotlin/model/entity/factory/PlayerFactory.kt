package model.entity.factory

import model.entity.GameEntity
import model.entity.attributes.*
import model.entity.behaviors.EffectsDecreaser
import model.entity.behaviors.InputHandler
import model.entity.facets.*
import model.entity.types.BaseType
import model.entity.types.Player
import view.views.play.resources.GameTiles

class PlayerFactory: EntityFactory() {

    override fun getDefault(): GameEntity<Player> {
        return createEntity()
    }

    override fun createEntity(): GameEntity<Player> = newAliveGameEntityOfType(
            Player,
            mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.PLAYER), EntityLevel(), EntityInventory(), Effects()),
            mutableListOf(InputHandler(), EffectsDecreaser()),
            mutableListOf(Movable(), ViewMover(), Hitable(), CanEquipInventoryItems(), CanTakeOffInventoryItems()))
}