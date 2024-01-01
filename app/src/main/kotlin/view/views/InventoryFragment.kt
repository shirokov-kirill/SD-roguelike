package view.views

import model.entity.GameEntity
import model.entity.attributes.inventory
import model.entity.types.Equipment
import model.entity.types.Player
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class InventoryFragment(
    player: GameEntity<Player>,
    width: Int,
    onEquip: (GameEntity<Equipment>) -> Unit,
    onTakeOff: (GameEntity<Equipment>) -> Unit
) : Fragment {

    override val root = Components.vbox()
        .withSize(width, player.inventory.size + 1)
        .build().apply {
            addComponent(Components.hbox()
                .withSpacing(1)
                .withSize(width, 1)
                .build().apply {
                    addComponent(Components.label().withText("").withSize(1, 1))
                    addComponent(Components.header().withText("Name").withSize(NAME_COLUMN_WIDTH, 1))
                    addComponent(Components.header().withText("Actions").withSize(ACTIONS_COLUMN_WIDTH, 1))
                }
            )
            player.inventory.forEach { item ->
                val row = InventoryRowFragment(width, item)
                addFragment(row).apply {
                    row.equipButton.onActivated {
                        onEquip(item)
                    }
                    row.takeOffButton.onActivated {
                        onTakeOff(item)
                    }
                }
            }
        }

    companion object {
        const val NAME_COLUMN_WIDTH = 15
        const val ACTIONS_COLUMN_WIDTH = 10
    }
}