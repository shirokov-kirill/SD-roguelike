package controller

import GameConfig
import controller.world.generator.GameWorldBuilder
import model.StateModificationsHandler
import model.entity.factory.AncientMobsFactory
import model.entity.factory.EquipmentFactory
import model.state.AdditionalInfo
import model.view.state.Game
import org.hexworks.zircon.api.data.Size3D

/**
 * This class represents a factory
  *that creates StateModificationsHandler
 */
class Initializer {

    companion object {

        /**
         * declares static method Initializer.initialize( type, filepath )
         * that manages loading or creating new game from sources
         */
        fun initialize(
            type: GameWorldBuilder.GameBuilderType = GameWorldBuilder.GameBuilderType.GENERATE,
            filePath: String = "src/main/resources/map.txt",
            difficulty: Difficulty,
            visibleSize: Size3D = GameConfig.GAME_AREA_SIZE
        ): StateModificationsHandler {
            val (gameWorld, player) = GameWorldBuilder()
                .passLoadingType(type)
                .passFilePath(filePath)
                .passDifficulty(difficulty)
                .withMobsFactory(AncientMobsFactory())
                .withEquipmentFactory(EquipmentFactory())
                .build(visibleSize)

            val game = Game(
                gameWorld,
                player
            )
            return StateModificationsHandler(game, AdditionalInfo())
        }
    }
}