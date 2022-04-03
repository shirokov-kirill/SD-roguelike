package controller

import model.entity.GameEntity
import model.view.state.GameWorld
import org.hexworks.zircon.api.screen.Screen
import model.entity.types.Player
import org.hexworks.zircon.api.uievent.UIEvent

data class GameContext(
    val world: GameWorld,
    val screen: Screen,
    val uiEvent: UIEvent,
    val player: GameEntity<Player>
)