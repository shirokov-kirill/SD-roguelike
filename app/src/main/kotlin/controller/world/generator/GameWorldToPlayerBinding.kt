package controller.world.generator

import model.entity.GameEntity
import model.entity.types.Player
import model.view.state.GameWorld

class GameWorldToPlayerBinding(private val gameWorld: GameWorld, private val player: GameEntity<Player>){

    operator fun component1(): GameWorld = gameWorld
    operator fun component2(): GameEntity<Player> = player

}