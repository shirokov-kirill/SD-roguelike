import org.hexworks.zircon.api.color.TileColor

/*
Implements colors
 */

object GameWorldColors {

    val WALL_FOREGROUND = TileColor.fromString("#75715E")
    val WALL_BACKGROUND = TileColor.fromString("#3E3D32")

    val FLOOR_FOREGROUND = TileColor.fromString("#75715E")
    val FLOOR_BACKGROUND = TileColor.fromString("#1E2320")

    val PLAYER_FOREGROUND = TileColor.fromString("#FFCD22")
    val SCARED_MONSTER_FOREGROUND = TileColor.fromString("#008000")
    val AGRESSIVE_MONSTER_FOREGROUND = TileColor.fromString("#FF0000")
    val STANDING_MONSTER_FOREGROUND = TileColor.fromString("#0000FF")
    val DROPPED_EQUIPMENT = TileColor.fromString("#FFFFFF")
}