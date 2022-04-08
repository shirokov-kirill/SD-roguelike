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
            filePath: String = "",
            difficulty: Difficulty
        ): StateModificationsHandler {
            val gameWorld = GameWorldBuilder(GameConfig.WORLD_SIZE)
                .passLoadingType(type, filePath)
                .proceed()
                .build(GameConfig.GAME_AREA_SIZE)

            val player = EntityFactory.createPlayer()

            gameWorld.addEntity(player, true)

            var monstersCount = 0
            when(difficulty){
                Difficulty.EASY -> monstersCount = 10
                Difficulty.MEDIUM -> monstersCount = 15
                Difficulty.HARD -> monstersCount = 20
                Difficulty.EXTREME -> monstersCount = 35
            }

            var equipmentCount = 15

            while (monstersCount > 0) {
                val monster = EntityFactory.createMonster()
                gameWorld.addEntity(monster, true, GameConfig.WORLD_SIZE)
                monstersCount--
            }

            while (equipmentCount > 0) {
                val equipment = EntityFactory.createEqiupment()
                gameWorld.addEntity(equipment, true, GameConfig.WORLD_SIZE)
                equipmentCount--
            }

            val game = Game(
                gameWorld,
                player
            )
            return StateModificationsHandler(game, AdditionalInfo())
        }
    }
}