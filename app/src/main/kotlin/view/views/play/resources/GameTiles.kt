package view.views.play.resources

import GameWorldColors.FLOOR_BACKGROUND
import GameWorldColors.FLOOR_FOREGROUND
import GameWorldColors.WALL_BACKGROUND
import GameWorldColors.WALL_FOREGROUND
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.Symbols

object GameTiles {

    val EMPTY: CharacterTile = Tile.empty()

    val FLOOR: CharacterTile = Tile.newBuilder()
        .withCharacter(Symbols.INTERPUNCT)                  // 1
        .withForegroundColor(FLOOR_FOREGROUND)              // 2
        .withBackgroundColor(FLOOR_BACKGROUND)              // 3
        .buildCharacterTile()                               // 4

    val WALL: CharacterTile = Tile.newBuilder()
        .withCharacter('#')
        .withForegroundColor(WALL_FOREGROUND)
        .withBackgroundColor(WALL_BACKGROUND)
        .buildCharacterTile()

}