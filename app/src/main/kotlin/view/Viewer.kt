package view

import model.state.AdditionalInfo
import model.view.state.Game
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.view.base.BaseView
import view.views.PlayView
import view.views.StartView

/*
Viewer is a main view part class
that shows sufficient screen according to
InterfaceCommands from controller part
 */

class Viewer {

    companion object {

        private var tileGrid = SwingApplications.startTileGrid(
            AppConfig.newBuilder()
                .withSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT)
                .withDefaultTileset(GameConfig.TILESET)
                .build()
        )

        private fun BaseView.renderView() {
            tileGrid.clear()
            dock()
        }
        fun renderToInventory(game: Game?, additionalInfo: AdditionalInfo?) {
            PlayView(tileGrid, game!!, additionalInfo!!, true).renderView()
        }

        fun renderPlay(game: Game?, additionalInfo: AdditionalInfo?) {
            PlayView(tileGrid, game!!, additionalInfo!!, false).renderView()
        }

        fun renderStart() {
            StartView(tileGrid).renderView()
        }
    }

}