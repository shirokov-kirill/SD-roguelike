package model.view.state.resourses

import view.views.play.resources.GameTiles

object GameBlockFactory {

    fun floor() = GameBlock(GameTiles.FLOOR, )

    fun wall() = GameBlock(GameTiles.WALL)

}