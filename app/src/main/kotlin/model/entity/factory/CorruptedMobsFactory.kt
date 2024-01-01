package model.entity.factory

import model.entity.GameEntity
import model.entity.attributes.*
import model.entity.behaviors.AggressiveMove
import model.entity.behaviors.EffectsDecreaser
import model.entity.behaviors.ScaryMove
import model.entity.behaviors.StandingMove
import model.entity.facets.Hittable
import model.entity.facets.Movable
import model.entity.facets.Cloneable
import model.entity.types.Monster
import view.views.play.resources.GameTiles

class CorruptedMobsFactory : MobsFactory() {

    override fun getDefault(): GameEntity<Monster> {
        return createEntity()
    }

    override fun createEntity(): GameEntity<Monster> = createMonsterWithProbability()

    private fun createMonsterWithProbability(): GameEntity<Monster> {
        val res = Math.random()
        var monster: GameEntity<Monster>? = null
        monster = if (res < 0.33) {
            createAggressiveCorrupted()
        } else if (res < 0.66) {
            createScaredCorrupted()
        } else {
            createStandingCorrupted()
        }
        if (Math.random() > 0.8) {
            monster.applyEffect(model.entity.attributes.effects.Cloneable(25))
        }
        return monster
    }

    private fun createScaredCorrupted() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.SCARED_CORRUPTED),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(ScaryMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hittable(), Cloneable())
    )

    private fun createStandingCorrupted() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.STANDING_CORRUPTED),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(StandingMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hittable(), Cloneable())
    )

    private fun createAggressiveCorrupted() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.AGGRESSIVE_CORRUPTED),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(AggressiveMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hittable(), Cloneable())
    )

}