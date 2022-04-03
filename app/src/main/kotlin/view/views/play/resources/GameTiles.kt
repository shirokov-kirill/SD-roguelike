package view.views.play.resources

import GameWorldColors.FLOOR_BACKGROUND
import GameWorldColors.FLOOR_FOREGROUND
import GameWorldColors.PLAYER_FOREGROUND
import GameWorldColors.WALL_BACKGROUND
import GameWorldColors.WALL_FOREGROUND
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.Symbols

object GameTiles {

    val EMPTY: CharacterTile = Tile.empty()

    val FLOOR: CharacterTile = Tile.newBuilder()
        .withCharacter(Symbols.INTERPUNCT)
        .withForegroundColor(FLOOR_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val WALL: CharacterTile = Tile.newBuilder()
        .withCharacter('#')
        .withForegroundColor(WALL_FOREGROUND)
        .withBackgroundColor(WALL_BACKGROUND)
        .buildCharacterTile()

    val PLAYER: CharacterTile = Tile.newBuilder()
        .withCharacter('@')
        .withForegroundColor(PLAYER_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()
}