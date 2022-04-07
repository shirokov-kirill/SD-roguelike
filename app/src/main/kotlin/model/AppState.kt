package model

import kotlinx.coroutines.Job
import model.state.AdditionalInfo
import model.view.state.Game
import model.view.state.GameWorld
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

class AppState (private val game: Game, private val info: AdditionalInfo) {

    fun getGame(): Game {
        return game
    }

    fun getInfo(): AdditionalInfo {
        return info
    }

    fun updateGame(screen: Screen, uiEvent: UIEvent){
        game.updateWorld(screen, uiEvent)
    }

    fun updateInfo() {
        info.update(game)
    }

}