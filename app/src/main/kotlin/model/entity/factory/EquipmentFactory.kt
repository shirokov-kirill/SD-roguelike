package model.entity.factory

import model.entity.GameEntity
import model.entity.attributes.EntityPosition
import model.entity.attributes.EntityTile
import model.entity.attributes.InventoryItemChars
import model.entity.types.*
import view.views.play.resources.GameTiles
import kotlin.math.floor

class EquipmentFactory : EntityFactory() {

    override fun getDefault(): GameEntity<Equipment> =
        newEquipmentOfType(DefaultEquipment, mutableListOf(InventoryItemChars(0, 0))) as GameEntity<Equipment>

    override fun createEntity(): GameEntity<out Equipment> = createEquipmentWithProbability()

    private fun createEquipmentWithProbability(): GameEntity<out Equipment> {
        val res = Math.random()
        return if (res < 0.25) {
            createHeadEquipment()
        } else if (res < 0.5) {
            createBodyEquipment()
        } else if (res < 0.75) {
            createHandEquipment()
        } else {
            createLegEquipment()
        }
    }

    private fun createHeadEquipment() = newEquipmentOfType(
        HeadEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(
                initialName = HEAD_EQUIPMENT[floor(Math.random() * (HEAD_EQUIPMENT.size - 1))
                    .toInt()]
            )
        ),
    )

    private fun createBodyEquipment() = newEquipmentOfType(
        BodyEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(
                initialName = BODY_EQUIPMENT[floor(Math.random() * (BODY_EQUIPMENT.size - 1))
                    .toInt()]
            )
        )
    )

    private fun createHandEquipment() = newEquipmentOfType(
        HandEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(
                initialName = HAND_EQUIPMENT[floor(Math.random() * (HAND_EQUIPMENT.size - 1))
                    .toInt()]
            )
        )
    )

    private fun createLegEquipment() = newEquipmentOfType(
        LegEquipment,
        mutableListOf(
            EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(
                initialName = LEG_EQUIPMENT[floor(Math.random() * (LEG_EQUIPMENT.size - 1))
                    .toInt()]
            )
        )
    )

}