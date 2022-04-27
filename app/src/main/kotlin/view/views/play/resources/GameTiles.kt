package view.views.play.resources

import GameWorldColors.AGRESSIVE_MONSTER_FOREGROUND
import GameWorldColors.DROPPED_EQUIPMENT
import GameWorldColors.FLOOR_BACKGROUND
import GameWorldColors.FLOOR_FOREGROUND
import GameWorldColors.PLAYER_FOREGROUND
import GameWorldColors.SCARED_MONSTER_FOREGROUND
import GameWorldColors.STANDING_MONSTER_FOREGROUND
import GameWorldColors.WALL_BACKGROUND
import GameWorldColors.WALL_FOREGROUND
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

    val AGRESSIVE_ANCIENT: CharacterTile = Tile.newBuilder()
        .withCharacter('A')
        .withForegroundColor(AGRESSIVE_MONSTER_FOREGROUND)
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

    val AGRESSIVE_CORRUPTED: CharacterTile = Tile.newBuilder()
        .withCharacter('C')
        .withForegroundColor(AGRESSIVE_MONSTER_FOREGROUND)
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