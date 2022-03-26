package model

import model.view.state.GameWorld

class StateModificationsHandler() {

    private val appState = AppState()

    fun loadMap(map: GameWorld) {
        appState.setMap(map)
    }

    fun getMap(): GameWorld? {
        return appState.getMap()
    }
}