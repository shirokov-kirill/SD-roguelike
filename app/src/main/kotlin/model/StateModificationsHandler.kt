package model

import model.state.AdditionalInfo
import model.view.state.Game
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

class StateModificationsHandler(game: Game, additionalInfo: AdditionalInfo) {

    private val currentAppState = AppState(game, additionalInfo)

    fun getGame(): Game {
        return currentAppState.getGame()
    }

    fun getAdditionalInfo(): AdditionalInfo {
        return currentAppState.getInfo()
    }

    fun updateCurrentGame(screen: Screen, uiEvent: UIEvent): Game{
        currentAppState.updateGame(screen, uiEvent)
        return currentAppState.getGame()
    }

}