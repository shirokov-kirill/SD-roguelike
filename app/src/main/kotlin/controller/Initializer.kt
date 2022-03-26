package controller

import model.StateModificationsHandler

class Initializer {
    companion object {

        val GENERATE = "GENERATE"
        val LOAD = "LOAD"

        fun initialize(type: String = GENERATE, filePath: String = "", mapWidth: Int = 0, mapHeight: Int = 0): StateModificationsHandler {
            val stateModificationsHandler = StateModificationsHandler()
            if(type == LOAD){
                //TODO load Map from filePath
            } else {
                val map = GameWorldBuilder(GameConfig.WORLD_SIZE)
                    .makeCaves()
                    .build(GameConfig.GAME_AREA_SIZE)
                stateModificationsHandler.loadMap(map)
            }
            return stateModificationsHandler
        }
    }
}