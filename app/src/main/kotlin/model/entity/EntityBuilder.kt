package model.entity

import model.entity.attributes.EntityPosition
import model.entity.attributes.EntityTile
import model.entity.types.Player
import view.views.play.resources.GameTiles
import controller.GameContext
import model.entity.attributes.EntityDirection
import model.entity.behaviors.InputHandler
import model.entity.facets.Movable
import model.entity.facets.ViewMover
import model.entity.types.Empty
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType

fun <T : EntityType> newGameEntityOfType(
    type: T,
    init: EntityBuilder<T, GameContext>.() -> Unit
) = newEntityOfType(type, init)

object EntityFactory {

    fun createPlayer() = newGameEntityOfType(Player) {
        attributes(EntityPosition(), EntityTile(GameTiles.PLAYER), EntityDirection())
        behaviors(InputHandler)
        facets(Movable, ViewMover)
    }

    fun getEmptyEntity() = newGameEntityOfType(Empty) {
        attributes(EntityPosition(), EntityTile())
        behaviors()
        facets()
    }
}