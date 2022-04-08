package model.entity.attributes

import kotlinx.collections.immutable.PersistentList
import model.entity.EntityFactory
import model.entity.GameEntity
import model.entity.types.Equipment
import org.hexworks.cobalt.databinding.api.extension.toProperty

class EntityInventory(
    initialInventory: MutableList<GameEntity<Equipment>> = mutableListOf()
) : Attribute{

    private var inventoryProperty = initialInventory.toProperty()
    private var equippedItemsProperty = mutableListOf(
        EntityFactory.getDefaultEquipment(),
        EntityFactory.getDefaultEquipment(),
        EntityFactory.getDefaultEquipment(),
        EntityFactory.getDefaultEquipment()).toProperty()

    var inventory: PersistentList<GameEntity<Equipment>> by inventoryProperty.asDelegate()
    var equippedItems: PersistentList<GameEntity<Equipment>> by equippedItemsProperty.asDelegate()
}