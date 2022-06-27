package model

import controller.GameContext
import controller.messages.EquipItem
import controller.messages.TakeOffItem
import model.entity.GameEntity
import model.entity.types.Equipment
import model.state.AdditionalInfo
import model.view.state.Game
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

class AppState(private val game: Game, private val info: AdditionalInfo) {

    fun getGame(): Game {
        return game
    }

    fun getInfo(): AdditionalInfo {
        return info
    }

    fun updateGame(screen: Screen, uiEvent: UIEvent) {
        game.updateWorld(screen, uiEvent)
    }

    fun updateInfo() {
        info.update(game)
    }

    fun performEquipItemAction(entity: GameEntity<Equipment>) {
        game.getPlayer()
            .receiveMessage(EquipItem(game.getPlayer(), entity, GameContext(game.getWorld(), null, null, null)))
    }

    fun performTakeOffItemAction(entity: GameEntity<Equipment>) {
        game.getPlayer()
            .receiveMessage(TakeOffItem(game.getPlayer(), entity, GameContext(game.getWorld(), null, null, null)))
    }

}