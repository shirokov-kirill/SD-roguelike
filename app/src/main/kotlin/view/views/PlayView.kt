package view.views

import controller.Controller
import model.state.AdditionalInfo
import model.view.state.Game
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.api.view.base.BaseView
import org.hexworks.zircon.internal.game.impl.GameAreaComponentRenderer
import view.views.play.resources.GameTiles

/*
PlayView implements main view of the game
with Inventory, AdditionalInfo and Game Map
 */

class PlayView(
    private val grid: TileGrid,
    private val game: Game,
    private val additionalInfo: AdditionalInfo,
    theme: ColorTheme = GameConfig.THEME
) : BaseView(grid, theme) {
    init {

        val world = game.getWorld()

        val sidebar = Components.panel()
            .withSize(GameConfig.SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
            .withDecorations(box(title = "Info"))
            .build()

        val logArea = Components.logArea()
            .withDecorations(box(title = "Inventory"))
            .withSize(GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH, GameConfig.INVENTORY_AREA_HEIGHT)
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
            .build()

        val gameComponent = Components.panel()
            .withSize(world.visibleSize.to2DSize())
            .withComponentRenderer(
                GameAreaComponentRenderer(
                    gameArea = world,
                    projectionMode = ProjectionMode.TOP_DOWN.toProperty(),
                    fillerTile = GameTiles.FLOOR
                )
            )
            .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
            .build()

        sidebar.addFragment(
            AdditionalInfoFragment(
                sidebar.contentSize.width,
                additionalInfo
            )
        )

        screen.addComponents(sidebar, logArea, gameComponent)

        screen.handleKeyboardEvents(KeyboardEventType.KEY_PRESSED) { event, _ ->
            Controller.throwKeyboardInput(event, screen)
            Processed
        }
    }
}