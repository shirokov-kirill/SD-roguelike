package model.entity

import model.entity.attributes.EntityPosition
import model.entity.attributes.EntityTile
import model.entity.types.Player
import view.views.play.resources.GameTiles
import controller.messages.GameMessage
import model.entity.attributes.Attribute
import model.entity.attributes.EntityDirection
import model.entity.behaviors.Behavior
import model.entity.behaviors.InputHandler
import model.entity.facets.Facet
import model.entity.facets.Movable
import model.entity.facets.ViewMover
import model.entity.types.BaseType
import model.entity.types.Empty

/*
EntityFactory is a factory which
supplies game with entities it needs
 */

private fun <T : BaseType> newGameEntityOfType(
    type: T,
    attributes: MutableList<Attribute>,
    behaviors: MutableList<Behavior<T>>,
    facets: MutableList<Facet<out GameMessage>>) = GameEntity(type, attributes, behaviors, facets)

object EntityFactory {

    fun createPlayer() = newGameEntityOfType(
        Player,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.PLAYER)),
        mutableListOf(InputHandler()),
        mutableListOf(Movable(), ViewMover())
    )

    fun getEmptyEntity() = newGameEntityOfType(
        Empty,
        mutableListOf(EntityPosition(), EntityTile()),
        mutableListOf(),
        mutableListOf()
    )
}