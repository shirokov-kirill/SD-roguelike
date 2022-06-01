package model.entity.factory

import model.entity.GameEntity
import model.entity.attributes.*
import model.entity.attributes.effects.Clonable
import model.entity.behaviors.AgressiveMove
import model.entity.behaviors.EffectsDecreaser
import model.entity.behaviors.ScaryMove
import model.entity.behaviors.StandingMove
import model.entity.facets.Cloneable
import model.entity.facets.Hitable
import model.entity.facets.Movable
import model.entity.types.BaseType
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
        if (res < 0.33) {
            monster = createAgressiveCorrupted()
        } else if (res < 0.66) {
            monster = createScaredCorrupted()
        } else {
            monster = createStandingCorrupted()
        }
        if (Math.random() > 0.8) {
            monster.applyEffect(Clonable(25))
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
        mutableListOf(Movable(), Hitable(), Cloneable())
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
        mutableListOf(Movable(), Hitable(), Cloneable())
    )

    private fun createAgressiveCorrupted() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(
            EntityDirection(),
            EntityPosition(),
            EntityTile(GameTiles.AGRESSIVE_CORRUPTED),
            EntityLevel(),
            EntityInventory(),
            Effects()
        ),
        mutableListOf(AgressiveMove(), EffectsDecreaser()),
        mutableListOf(Movable(), Hitable(), Cloneable())
    )

}