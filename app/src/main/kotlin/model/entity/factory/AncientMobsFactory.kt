package model.entity.factory

import model.entity.GameEntity
import model.entity.attributes.*
import model.entity.attributes.effects.Clonable
import model.entity.behaviors.*
import model.entity.facets.Hitable
import model.entity.facets.Movable
import model.entity.types.BaseType
import model.entity.types.Monster
import view.views.play.resources.GameTiles

class AncientMobsFactory: MobsFactory() {

    override fun getDefault(): GameEntity<Monster> {
        return createEntity()
    }

    override fun createEntity(): GameEntity<Monster> = createMonsterWithProbability()

    private fun createMonsterWithProbability(): GameEntity<Monster> {
        val res = Math.random()
        var monster: GameEntity<Monster>? = null
        if(res < 0.25){
            monster = createAgressiveAncient()
        } else if(res < 0.5){
            monster = createScaredAncient()
        } else if(res < 0.75){
            monster = createStandingAncient()
        } else {
            monster = createMutableGoingAncient()
        }
        if(Math.random() > 0.8){
            monster.applyEffect(Clonable(25))
        }
        return monster
    }

    private fun createScaredAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.SCARED_ANCIENT), EntityLevel(), EntityInventory(), Effects()),
        mutableListOf(ScaryMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hitable())
    )

    private fun createStandingAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.STANDING_ANCIENT), EntityLevel(), EntityInventory(), Effects()),
        mutableListOf(StandingMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hitable())
    )

    private fun createAgressiveAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.AGRESSIVE_ANCIENT), EntityLevel(), EntityInventory(), Effects()),
        mutableListOf(AgressiveMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hitable())
    )

    private fun createMutableGoingAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.AGRESSIVE_ANCIENT), EntityLevel(), EntityInventory(), Effects()),
        mutableListOf(MutableBehavior(), EffectsDecreaser()),
        mutableListOf(Movable(), Hitable())
    )

}