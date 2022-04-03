package view.views

import controller.Controller
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView
import view.InterfaceCommands

class StartView(
    private val grid: TileGrid
) : BaseView(grid, GameConfig.THEME) {
    init {
        val msg = "Welcome to Dungeon Runners."

        val header = Components.textBox(contentWidth = msg.length)
            .addHeader(msg)
            .addNewLine()
            .withAlignmentWithin(screen, ComponentAlignment.CENTER)
            .build()

        val startButton = Components.button()
            .withAlignmentAround(header, ComponentAlignment.BOTTOM_CENTER)
            .withText("PLAY")
            .withDecorations(box())
            .build()

        startButton.onActivated {
            Controller.throwMouseInput(InterfaceCommands.TO_PLAY)
        }

        screen.addComponents(header, startButton)
    }
}