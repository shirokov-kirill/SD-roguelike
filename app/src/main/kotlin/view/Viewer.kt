package view

import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig
import model.view.state.GameWorld
import view.views.PlayView
import view.views.StartView

class Viewer{

    companion object {

        private var tileGrid = SwingApplications.startTileGrid(
            AppConfig.newBuilder()
                .withSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT)
                .withDefaultTileset(GameConfig.TILESET)
                .build()
        )

        fun render(input: InterfaceCommands, map: GameWorld? = null){
            when(input){
                InterfaceCommands.START -> {
                    tileGrid.clear()
                    StartView(tileGrid).dock()
                }
                InterfaceCommands.TO_PLAY -> {
                    tileGrid.clear()
                    PlayView(tileGrid, map!!).dock()
                }
                else -> {
                    return
                }
            }
        }
    }

}