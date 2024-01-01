package model

import model.entity.GameEntity
import model.entity.types.Equipment
import model.state.AdditionalInfo
import model.view.state.Game
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

class StateModificationsHandler(game: Game, additionalInfo: AdditionalInfo) {

    private val currentAppState = AppState(game, additionalInfo)

    fun getGame(): Game {
        currentAppState.updateInfo()
        return currentAppState.getGame()
    }

    fun getAdditionalInfo(): AdditionalInfo {
        return currentAppState.getInfo()
    }

    fun updateCurrentGame(screen: Screen, uiEvent: UIEvent): Game{
        currentAppState.updateGame(screen, uiEvent)
        currentAppState.updateInfo()
        return currentAppState.getGame()
    }

    fun performEquipItemAction(entity: GameEntity<Equipment>): Game{
        currentAppState.performEquipItemAction(entity)
        currentAppState.updateInfo()
        return currentAppState.getGame()
    }

    fun performTakeOffItemAction(entity: GameEntity<Equipment>): Game {
        currentAppState.performTakeOffItemAction(entity)
        currentAppState.updateInfo()
        return currentAppState.getGame()
    }

}