package model.entity.attributes

import kotlinx.collections.immutable.PersistentList
import model.entity.attributes.effects.Effect
import org.hexworks.cobalt.databinding.api.extension.toProperty

class Effects(
    initialEffects: MutableList<Effect> = mutableListOf()
): Attribute {

    private var effectsProperty = initialEffects.toProperty()

    var effects: PersistentList<Effect> by effectsProperty.asDelegate()

}