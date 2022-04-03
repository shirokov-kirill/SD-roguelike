package controller

import model.view.state.GameWorld
import org.hexworks.amethyst.api.Context
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.zircon.api.screen.Screen
import model.entity.types.Player
import org.hexworks.zircon.api.uievent.UIEvent

typealias GameEntity<T> = Entity<T, GameContext>

data class GameContext(
    val world: GameWorld,
    val screen: Screen,
    val uiEvent: UIEvent,
    val player: GameEntity<Player>
) : Context