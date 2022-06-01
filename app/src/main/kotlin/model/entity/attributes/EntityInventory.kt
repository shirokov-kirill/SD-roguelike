package model.entity.attributes

import kotlinx.collections.immutable.PersistentList
import model.entity.GameEntity
import model.entity.factory.EquipmentFactory
import model.entity.types.Equipment
import org.hexworks.cobalt.databinding.api.extension.toProperty

class EntityInventory(
    initialInventory: MutableList<GameEntity<Equipment>> = mutableListOf()
) : Attribute{

    private var inventoryProperty = initialInventory.toProperty()
    private val equipmentFactory = EquipmentFactory()
    private var equippedItemsProperty = mutableListOf(
        equipmentFactory.getDefault(),
        equipmentFactory.getDefault(),
        equipmentFactory.getDefault(),
        equipmentFactory.getDefault(),).toProperty()

    var inventory: PersistentList<GameEntity<Equipment>> by inventoryProperty.asDelegate()
    var equippedItems: PersistentList<GameEntity<Equipment>> by equippedItemsProperty.asDelegate()

    override fun clone(): Attribute {
        return EntityInventory(mutableListOf())
    }
}