package view.views

import model.entity.GameEntity
import model.entity.attributes.name
import model.entity.types.Equipment
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class InventoryRowFragment(width: Int, item: GameEntity<Equipment>) : Fragment {

    val equipButton = Components.button()
        .withText("Equip")
        .withDecorations()
        .build()

    val takeOffButton = Components.button()
        .withText("Take off")
        .withDecorations()
        .build()

    override val root = Components.hbox()
        .withSpacing(2)
        .withSize(width, 1)
        .build().apply {
            addComponent(
                Components.label()
                    .withSize(InventoryFragment.NAME_COLUMN_WIDTH, 1)   // 8
                    .withText(item.name)
            )
            addComponent(equipButton)
            addComponent(takeOffButton)
        }
}