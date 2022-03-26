package model

import model.state.AdditionalInfo
import model.view.state.GameWorld

class AppState () {

    private var map : GameWorld? = null
    private val info = AdditionalInfo()

    fun getMap(): GameWorld? {
        return map
    }

    fun setMap(map: GameWorld) {
        this.map = map
    }
}