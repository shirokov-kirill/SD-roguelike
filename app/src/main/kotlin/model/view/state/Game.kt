package model.view.state

import kotlinx.coroutines.Job
import model.entity.GameEntity
import model.entity.types.Player
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

class Game(private val gameWorld: GameWorld, private val player: GameEntity<Player>) {

    fun getWorld(): GameWorld {
        return gameWorld
    }

    fun getPlayer(): GameEntity<Player> {
        return player
    }

    fun updateWorld(screen: Screen, event: UIEvent){
        gameWorld.update(screen, event, player)
    }

    companion object{

        fun create(gameWorld: GameWorld, player: GameEntity<Player>) = Game(gameWorld, player)

    }
}