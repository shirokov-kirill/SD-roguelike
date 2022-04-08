package model.entity

import model.entity.types.Player
import view.views.play.resources.GameTiles
import controller.messages.GameMessage
import model.entity.attributes.*
import model.entity.behaviors.AgressiveMove
import model.entity.behaviors.Behavior
import model.entity.behaviors.InputHandler
import model.entity.behaviors.ScaryMove
import model.entity.facets.Facet
import model.entity.facets.Hitable
import model.entity.facets.Movable
import model.entity.facets.ViewMover
import model.entity.types.BaseType
import model.entity.types.Empty
import model.entity.types.Monster

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
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.PLAYER), EntityLevel()),
        mutableListOf(InputHandler()),
        mutableListOf(Movable(), ViewMover(), Hitable())
    )

    fun createMonster() = createMonsterWithProbability()

    private fun createMonsterWithProbability(): GameEntity<Monster> {
        val res = Math.random()
        if(res < 0.33){
            return createAgressiveMonster()
        } else if(res < 0.66){
            return createScaredMonster()
        } else {
            return createStandingMonster()
        }
    }

    private fun createScaredMonster() = newGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.SCARED_MONSTER), EntityLevel()),
        mutableListOf(ScaryMove()),
        mutableListOf(Movable(), Hitable())
    )

    private fun createStandingMonster() = newGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.STANDING_MONSTER), EntityLevel()),
        mutableListOf(),
        mutableListOf(Movable(), Hitable())
    )

    private fun createAgressiveMonster() = newGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.AGRESSIVE_MONSTER), EntityLevel()),
        mutableListOf(AgressiveMove()),
        mutableListOf(Movable(), Hitable())
    )

    fun getEmptyEntity() = newGameEntityOfType(
        Empty,
        mutableListOf(EntityPosition(), EntityTile()),
        mutableListOf(),
        mutableListOf()
    )
}