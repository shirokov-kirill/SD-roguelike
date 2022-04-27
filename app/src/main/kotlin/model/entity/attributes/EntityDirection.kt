package model.entity.attributes

import org.hexworks.cobalt.databinding.api.extension.toProperty

/*
This is an Attribute class that provides
entity with Direction
 */

class EntityDirection(
    initialDirection: Directions = Directions.TOP
) : Attribute{

    private val directionProperty = initialDirection.toProperty()

    var direction: Directions by directionProperty.asDelegate()

    override fun clone(): Attribute {
        return EntityDirection(direction)
    }
}