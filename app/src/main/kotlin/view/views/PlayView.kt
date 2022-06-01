package view.views

import GameConfig.DIALOG_SIZE
import controller.Controller
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.state.AdditionalInfo
import model.view.state.Game
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.ComponentDecorations.shadow
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.api.view.base.BaseView
import org.hexworks.zircon.internal.component.modal.EmptyModalResult
import org.hexworks.zircon.internal.game.impl.GameAreaComponentRenderer
import view.InterfaceCommands
import view.views.play.resources.GameTiles

/*
PlayView implements main view of the game
with Inventory, AdditionalInfo, Game Map and Log
 */

class PlayView(
    private val grid: TileGrid,
    private val game: Game,
    private val additionalInfo: AdditionalInfo,
    private val withInventory: Boolean,
    theme: ColorTheme = GameConfig.THEME
) : BaseView(grid, theme) {

    private var inventoryOpened: Boolean = false

    private fun openInventory() {
        Controller.onInventoryOpen()
        val panel = Components.panel()
            .withSize(DIALOG_SIZE)
            .withDecorations(box(title = "Inventory"), shadow())
            .build()

        val fragment = InventoryFragment(game.getPlayer(), DIALOG_SIZE.width - 3,
            { entity -> Controller.performEquipItemAction(entity) },
            { entity -> Controller.performTakeOffItemAction(entity) })

        panel.addFragment(fragment)

        val modal = ModalBuilder.newBuilder<EmptyModalResult>()
            .withParentSize(screen.size)
            .withComponent(panel)
            .withCenteredDialog(true)
            .build()

        panel.addComponent(Components.button()
            .withText("Close")
            .withAlignmentWithin(panel, ComponentAlignment.BOTTOM_LEFT)
            .build().apply {
                onActivated {
                    modal.close(EmptyModalResult)
                    Controller.onInventoryClose()
                    Processed
                }
            })

        modal.theme = GameConfig.THEME
        screen.openModal(modal)
    }

    init {

        val world = game.getWorld()

        val sidebar = Components.panel()
            .withSize(GameConfig.SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
            .withDecorations(box(title = "Info"))
            .build()

        val logArea = Components.logArea()
            .withDecorations(box(title = "Log"))
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
            if (event.code == KeyCode.KEY_I) {
                if (!inventoryOpened) {
                    openInventory()
                    inventoryOpened = true
                }
                return@handleKeyboardEvents Processed
            }
            Controller.throwKeyboardInput(event, screen)
            Processed
        }

        if (withInventory) {
            openInventory()
        }
    }
}