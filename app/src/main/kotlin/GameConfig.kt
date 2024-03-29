import controller.world.generator.GameWorldBuilder
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.Size3D

/**
 * Here are implemented all base
 * constants for game setup
 */

object GameConfig {

    val TILESET = CP437TilesetResources.rogueYun16x16()
    val THEME = ColorThemes.zenburnVanilla()
    const val SIDEBAR_WIDTH = 18
    const val INVENTORY_AREA_HEIGHT = 6

    const val WINDOW_WIDTH = 80
    const val WINDOW_HEIGHT = 50

    val WORLD_SIZE = Size3D.create(WINDOW_WIDTH * 2, WINDOW_HEIGHT * 2, 1)
    val DIALOG_SIZE = Size.create(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2)
    val GAME_AREA_SIZE = Size3D.create(
        xLength = WINDOW_WIDTH - SIDEBAR_WIDTH,
        yLength = WINDOW_HEIGHT - INVENTORY_AREA_HEIGHT,
        zLength = 1
    )

    val GAMEWORLD_BUILDER_FILE = "src/main/resources/map.txt"
    val GAMEWORLD_BUILDER = GameWorldBuilder.GameBuilderType.GENERATE
}