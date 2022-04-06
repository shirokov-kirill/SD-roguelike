package controller

import model.StateModificationsHandler
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.UIEvent
import view.InterfaceCommands
import view.Viewer

class Controller {

    companion object {
        private var isFinished = false
        private var stateModificationsHandler : StateModificationsHandler? = null

        /*
        start() method can be used to create all the game
        (suggest only to use once at the beginning)
         */

        fun start(){
            interpret(InterfaceCommands.START)
        }

        /*
        throwMouseInput() method can be used to handle MouseClicks from user
        that are not currently performed in the game
         */

        fun throwMouseInput(input: InterfaceCommands){
            interpret(input)
        }

        /*
        throwKeyboardInput() method can be used to parse clicks on Keyboard from user
        to InterfaceCommands
         */

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

        /*
        initializeGame() method is private and used to create ModificationHandler if not exists
        that mainly holds all the Game state
         */

        private fun initializeGame(){
            if(stateModificationsHandler == null){
                stateModificationsHandler = Initializer.initialize(GameWorldBuilder.GENERATE, "", Difficulty.EASY)
            }
        }

        /*
        interpret() method is private and used to respond to InterfaceCommands with
        proper modification of state and interface
         */

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