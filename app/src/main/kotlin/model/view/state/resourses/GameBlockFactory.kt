package model.view.state.resourses

import view.views.play.resources.GameTiles

object GameBlockFactory {
    val floor
        get() = GameBlock(GameTiles.FLOOR)

    val wall
        get() = GameBlock(GameTiles.WALL)

}