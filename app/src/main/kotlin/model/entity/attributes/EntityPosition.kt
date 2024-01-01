package model.entity.attributes

import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.data.Position3D

/*
This is an Attribute class that provides
entity with Position
*/

class EntityPosition(
    initialPosition: Position3D = Position3D.unknown()
) : Attribute{

    private val positionProperty = initialPosition.toProperty()

    var position: Position3D by positionProperty.asDelegate()

    override fun clone(): Attribute {
        return  EntityPosition()
    }
}