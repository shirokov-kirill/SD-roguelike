package model.entity.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.extension.toProperty

class EntityDirection(
    initialDirection: Directions = Directions.TOP
) : BaseAttribute() {

    private val directionProperty = initialDirection.toProperty()

    var direction: Directions by directionProperty.asDelegate()
}