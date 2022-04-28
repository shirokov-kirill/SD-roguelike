package controller

import model.StateModificationsHandler
import model.entity.factory.AncientMobsFactory
import model.entity.factory.CorruptedMobsFactory
import model.entity.factory.EquipmentFactory
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
            filePath: String = "",
            difficulty: Difficulty
        ): StateModificationsHandler {
            val (gameWorld, player) = GameWorldBuilder(GameConfig.WORLD_SIZE)
                .passLoadingType(type)
                .passFilePath(filePath)
                .passDifficulty(difficulty)
                .withMobsFactory(AncientMobsFactory())
                .withEquipmentFactory(EquipmentFactory())
                .build(GameConfig.GAME_AREA_SIZE)

            val game = Game(
                gameWorld,
                player
            )
            return StateModificationsHandler(game, AdditionalInfo())
        }
    }
}