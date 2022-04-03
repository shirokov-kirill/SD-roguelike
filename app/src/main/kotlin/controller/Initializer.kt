package controller

import model.StateModificationsHandler
import model.entity.EntityFactory
import model.state.AdditionalInfo
import model.view.state.Game

class Initializer {
    companion object {

        fun initialize(
            type: String = GameWorldBuilder.GENERATE,
            filePath: String = ""
        ): StateModificationsHandler {
            val gameWorld = GameWorldBuilder(GameConfig.WORLD_SIZE)
                .passLoadingType(type, filePath)
                .proceed()
                .build(GameConfig.GAME_AREA_SIZE)

            val player = EntityFactory.createPlayer()

            gameWorld.addEntity(player, true)

            val game = Game(
                gameWorld,
                player
            )
            return StateModificationsHandler(game, AdditionalInfo())
        }
    }
}