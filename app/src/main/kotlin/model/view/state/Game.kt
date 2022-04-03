package model.view.state

import controller.GameContext
import controller.GameWorldBuilder
import model.entity.types.Player
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

class Game(private val gameWorld: GameWorld, private val player: Entity<Player, GameContext>) {

    fun getWorld(): GameWorld {
        return gameWorld
    }

    fun updateWorld(screen: Screen, event: UIEvent) {
        gameWorld.update(screen, event, player)
    }

    companion object{

        fun create(gameWorld: GameWorld, player: Entity<Player, GameContext>) = Game(gameWorld, player)

    }
}