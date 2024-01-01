package model.entity.facets

import controller.messages.*
import model.entity.attributes.takeOffItem

class CanTakeOffInventoryItems : BaseFacet<TakeOffItem>(TakeOffItem::class) {

    override fun receive(message: TakeOffItem): Response {
        val item = message.equipment
        val toEntity = message.entity
        if (toEntity.takeOffItem(item)) {
            return Consumed
        }
        return Pass
    }

}