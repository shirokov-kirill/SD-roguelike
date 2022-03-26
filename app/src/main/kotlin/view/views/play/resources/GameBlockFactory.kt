package view.views.play.resources

object GameBlockFactory {

    fun floor() = GameBlock(GameTiles.FLOOR)

    fun wall() = GameBlock(GameTiles.WALL)

}