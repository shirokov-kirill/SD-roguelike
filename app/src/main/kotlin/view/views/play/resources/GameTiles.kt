package view.views.play.resources

import view.views.play.resources.GameWorldColors.AGGRESSIVE_MONSTER_FOREGROUND
import view.views.play.resources.GameWorldColors.FLOOR_BACKGROUND
import view.views.play.resources.GameWorldColors.FLOOR_FOREGROUND
import view.views.play.resources.GameWorldColors.PLAYER_FOREGROUND
import view.views.play.resources.GameWorldColors.SCARED_MONSTER_FOREGROUND
import view.views.play.resources.GameWorldColors.STANDING_MONSTER_FOREGROUND
import view.views.play.resources.GameWorldColors.WALL_BACKGROUND
import view.views.play.resources.GameWorldColors.WALL_FOREGROUND
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.Symbols

/*
GameTiles identify Tiles
(how they look)
 */

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

    val SCARED_ANCIENT: CharacterTile = Tile.newBuilder()
        .withCharacter('A')
        .withForegroundColor(SCARED_MONSTER_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val AGGRESSIVE_ANCIENT: CharacterTile = Tile.newBuilder()
        .withCharacter('A')
        .withForegroundColor(AGGRESSIVE_MONSTER_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val STANDING_ANCIENT: CharacterTile = Tile.newBuilder()
        .withCharacter('A')
        .withForegroundColor(STANDING_MONSTER_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val SCARED_CORRUPTED: CharacterTile = Tile.newBuilder()
        .withCharacter('C')
        .withForegroundColor(SCARED_MONSTER_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val AGGRESSIVE_CORRUPTED: CharacterTile = Tile.newBuilder()
        .withCharacter('C')
        .withForegroundColor(AGGRESSIVE_MONSTER_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val STANDING_CORRUPTED: CharacterTile = Tile.newBuilder()
        .withCharacter('C')
        .withForegroundColor(STANDING_MONSTER_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val DROPPED_EQUIPMENT: CharacterTile = Tile.newBuilder()
        .withCharacter('?')
        .withForegroundColor(GameWorldColors.DROPPED_EQUIPMENT)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()
}