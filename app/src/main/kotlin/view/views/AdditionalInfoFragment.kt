package view.views

import model.entity.attributes.hitPoints
import model.state.AdditionalInfo
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class AdditionalInfoFragment(
    width: Int,
    info: AdditionalInfo
) : Fragment {

    override val root = Components.vbox()
        .withSize(width, 30)                                                    // 1
        .withSpacing(1)                                                         // 2
        .build().apply {
            addComponent(Components.header().withText("Player"))                // 3
            addComponent(Components.listItem().withText("HP: " + info.getPlayerHitPoints()))
            addComponent(Components.listItem().withText("Damage: " + info.getPlayerDamage()))
            addComponent(Components.listItem().withText("Level: " + info.getPlayerLevel()))
            addComponent(Components.header().withText("Target"))
            if(info.getPlayerTarget() != null){
                addComponent(Components.listItem().withText("HP: " + info.getPlayerTarget()!!.hitPoints))
            }
        }
}