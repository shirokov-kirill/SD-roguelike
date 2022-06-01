package model.entity.factory

import model.entity.GameEntity
import model.entity.attributes.EntityPosition
import model.entity.attributes.EntityTile
import model.entity.attributes.InventoryItemChars
import model.entity.types.*
import view.views.play.resources.GameTiles
import javax.swing.text.html.parser.Entity

class EquipmentFactory : EntityFactory() {

    override fun getDefault(): GameEntity<Equipment> =
        newEquipmentOfType(DefaultEquipment, mutableListOf(InventoryItemChars(0, 0))) as GameEntity<Equipment>

    override fun createEntity(): GameEntity<out Equipment> = createEquipmentWithProbability()

    private fun createEquipmentWithProbability(): GameEntity<out Equipment> {
        val res = Math.random()
        if(res < 0.25){
            return createHeadEquipment()
        } else if(res < 0.5){
            return createBodyEquipment()
        } else if(res < 0.75){
            return createHandEquipment()
        } else {
            return createLegEquipment()
        }
    }

    private fun createHeadEquipment() = newEquipmentOfType(
        HeadEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = HEAD_EQUIPMENT[Math.floor(Math.random() * (HEAD_EQUIPMENT.size - 1))
                .toInt()])
        ),
    )

    private fun createBodyEquipment() = newEquipmentOfType(
        BodyEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = BODY_EQUIPMENT[Math.floor(Math.random() * (BODY_EQUIPMENT.size - 1))
                .toInt()])
        )
    )

    private fun createHandEquipment() = newEquipmentOfType(
        HandEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = HAND_EQUIPMENT[Math.floor(Math.random() * (HAND_EQUIPMENT.size - 1))
                .toInt()])
        )
    )

    private fun createLegEquipment() = newEquipmentOfType(
        LegEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = LEG_EQUIPMENT[Math.floor(Math.random() * (LEG_EQUIPMENT.size - 1))
                .toInt()])
        )
    )

}