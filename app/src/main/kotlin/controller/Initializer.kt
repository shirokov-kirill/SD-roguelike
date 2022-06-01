package controller

import model.StateModificationsHandler
import model.entity.EntityFactory
import model.state.AdditionalInfo
import model.view.state.Game

/*
This class represents a factory
that creates StateModificationsHandler
 */

class Initializer {

    companion object {

        /*
        declares static method Initializer.initialize( type, filepath )
        that manages loading or creating new game from sources
        */

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