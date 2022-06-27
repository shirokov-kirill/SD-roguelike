package model.entity.factory

import view.views.play.resources.GameTiles
import controller.messages.GameMessage
import model.entity.GameEntity
import model.entity.attributes.*
import model.entity.behaviors.*
import model.entity.facets.*
import model.entity.types.*

abstract class EntityFactory {

    /*
    EntityFactory is a factory which
    supplies game with entities it needs
    */

    protected fun <T : BaseType> newGameEntityOfType(
        type: T,
        attributes: MutableList<Attribute>
    ) = GameEntity(type, attributes, mutableListOf(), mutableListOf())

    protected fun <T : Equipment> newEquipmentOfType(
        type: T,
        attributes: MutableList<Attribute>
    ) = GameEntity(type, attributes, mutableListOf(), mutableListOf())

    protected fun <T : Creature> newAliveGameEntityOfType(
        type: T,
        attributes: MutableList<Attribute>,
        behaviors: MutableList<Behavior>,
        facets: MutableList<Facet<out GameMessage>>
    ) = GameEntity(type, attributes, behaviors, facets)

    abstract fun getDefault(): GameEntity<out BaseType>

    abstract fun createEntity(): GameEntity<out BaseType>

    companion object {

        fun getEmptyEntity(): GameEntity<Empty> =
            GameEntity(Empty, mutableListOf(EntityTile(GameTiles.EMPTY)), mutableListOf(), mutableListOf())

    }
}