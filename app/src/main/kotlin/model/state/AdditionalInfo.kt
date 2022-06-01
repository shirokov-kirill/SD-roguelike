package model.state

import model.entity.GameEntity
import model.entity.attributes.*
import model.entity.types.Creature
import model.view.state.Game

/*
Can handle Additional Info
about player
and
about whole game
 */

class AdditionalInfo {
    private var playerHitPoints: Int = 0
    private var playerDamage: Int = 0
    private var playerLevel: Int = 0
    private var playerTarget: GameEntity<Creature>? = null

    fun update(game: Game): AdditionalInfo {
        val player = game.getPlayer()
        this.playerHitPoints = player.hitPoints
        this.playerDamage = player.damage
        this.playerLevel = player.level
        val targetPosition = player.targetPosition
        this.playerTarget = game.getWorld().getCreatureOnPosition(targetPosition)
        return this
    }

    fun getPlayerHitPoints(): Int {
        return playerHitPoints
    }

    fun getPlayerDamage(): Int {
        return playerDamage
    }

    fun getPlayerLevel(): Int {
        return playerLevel
    }

    fun getPlayerTarget(): GameEntity<Creature>? {
        return playerTarget
    }
}