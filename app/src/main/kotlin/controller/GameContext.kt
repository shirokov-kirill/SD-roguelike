package controller

import model.entity.GameEntity
import model.entity.types.Creature
import model.view.state.GameWorld
import org.hexworks.zircon.api.screen.Screen
import model.entity.types.Player
import org.hexworks.zircon.api.uievent.UIEvent

/*
This class represents an object that passes to the Engine
in order to inform it about new UIEvent happened
 */

data class GameContext(
    val world: GameWorld,
    val screen: Screen?,
    val uiEvent: UIEvent?,
    val player: GameEntity<Player>?
)