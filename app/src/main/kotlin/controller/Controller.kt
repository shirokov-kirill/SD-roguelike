package controller

import model.StateModificationsHandler
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.UIEvent
import org.hexworks.zircon.api.uievent.UIEventType
import view.InterfaceCommands
import view.Viewer

class Controller {

    companion object {
        private var isFinished = false
        private var stateModificationsHandler : StateModificationsHandler? = null

        fun start(){
            interpret(InterfaceCommands.START)
        }

        fun throwMouseInput(input: InterfaceCommands){
            interpret(input)
        }

        fun throwKeyboardInput(input: KeyboardEvent, screen: Screen){
            when(input.key) {
                "w" -> interpret(InterfaceCommands.GO_TOP, input, screen)
                "a" -> interpret(InterfaceCommands.GO_LEFT, input, screen)
                "s" -> interpret(InterfaceCommands.GO_BOTTOM, input, screen)
                "d" -> interpret(InterfaceCommands.GO_RIGHT, input, screen)
                " " -> interpret(InterfaceCommands.HIT, input, screen)
                else -> {
                    print(input.key)
                }
            }
        }

        private fun initializeGame(){
            stateModificationsHandler = Initializer.initialize(GameWorldBuilder.GENERATE, "")
        }

        private fun interpret(input: InterfaceCommands, uiEvent: UIEvent? = null, screen: Screen? = null){
            when(input){
                InterfaceCommands.START -> {
                    initializeGame()
                    Viewer.render(InterfaceCommands.START, null, null)
                }
                InterfaceCommands.TO_PLAY -> {
                    Viewer.render(InterfaceCommands.TO_PLAY, stateModificationsHandler?.getGame(), stateModificationsHandler?.getAdditionalInfo())
                }
                InterfaceCommands.GO_RIGHT -> {
                    Viewer.render(InterfaceCommands.TO_PLAY, stateModificationsHandler?.updateCurrentGame(screen!!, uiEvent!!), stateModificationsHandler?.getAdditionalInfo())
                }
                InterfaceCommands.GO_BOTTOM -> {
                    Viewer.render(InterfaceCommands.TO_PLAY, stateModificationsHandler?.updateCurrentGame(screen!!, uiEvent!!), stateModificationsHandler?.getAdditionalInfo())
                }
                InterfaceCommands.GO_LEFT -> {
                    Viewer.render(InterfaceCommands.TO_PLAY, stateModificationsHandler?.updateCurrentGame(screen!!, uiEvent!!), stateModificationsHandler?.getAdditionalInfo())
                }
                InterfaceCommands.GO_TOP -> {
                    Viewer.render(InterfaceCommands.TO_PLAY, stateModificationsHandler?.updateCurrentGame(screen!!, uiEvent!!), stateModificationsHandler?.getAdditionalInfo())
                }
                InterfaceCommands.HIT -> {
                    Viewer.render(InterfaceCommands.TO_PLAY, stateModificationsHandler?.updateCurrentGame(screen!!, uiEvent!!), stateModificationsHandler?.getAdditionalInfo())
                }
                else -> {
                    return
                }
            }
        }
    }
}