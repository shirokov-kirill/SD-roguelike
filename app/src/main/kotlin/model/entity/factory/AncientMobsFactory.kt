package model.entity.factory

import model.entity.GameEntity
import model.entity.attributes.*
import model.entity.attributes.effects.Cloneable
import model.entity.behaviors.*
import model.entity.facets.Hittable
import model.entity.facets.Movable
import model.entity.types.Monster
import view.views.play.resources.GameTiles

class AncientMobsFactory : MobsFactory() {

    override fun getDefault(): GameEntity<Monster> {
        return createEntity()
    }

    override fun createEntity(): GameEntity<Monster> = createMonsterWithProbability()

    private fun createMonsterWithProbability(): GameEntity<Monster> {
        val res = Math.random()
        var monster: GameEntity<Monster>? = null
        monster = if (res < 0.25) {
            createAggressiveAncient()
        } else if (res < 0.5) {
            createScaredAncient()
        } else if (res < 0.75) {
            createStandingAncient()
        } else {
            createMutableGoingAncient()
        }
        if (Math.random() > 0.8) {
            monster.applyEffect(Cloneable(25))
        }
        return monster
    }

    private fun createScaredAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.SCARED_ANCIENT),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(ScaryMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hittable())
    )

    private fun createStandingAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.STANDING_ANCIENT),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(StandingMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hittable())
    )

    private fun createAggressiveAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.AGGRESSIVE_ANCIENT),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(AggressiveMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hittable())
    )

    private fun createMutableGoingAncient() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.AGGRESSIVE_ANCIENT),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(MutableBehavior(), EffectsDecreaser()),
        mutableListOf(Movable(), Hittable())
    )

}