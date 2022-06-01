package model.entity.facets

import controller.messages.Consumed
import controller.messages.EquipItem
import controller.messages.Pass
import controller.messages.Response
import model.entity.attributes.equipItem

class CanEquipInventoryItems: BaseFacet<EquipItem>(EquipItem::class) {

    override fun receive(message: EquipItem): Response {
        val item = message.equipment
        val toEntity = message.entity
        if(toEntity.equipItem(item)) {
            return Consumed
        }
        return Pass
    }

}