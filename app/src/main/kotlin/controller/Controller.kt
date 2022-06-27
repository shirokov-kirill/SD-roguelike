package controller

import controller.world.generator.GameWorldBuilder
import model.StateModificationsHandler
import model.entity.GameEntity
import model.entity.types.Equipment
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.UIEvent
import view.Viewer

class Controller {

    companion object {
        var isActive: Boolean = false
        var isFinished = false
        private var stateModificationsHandler: StateModificationsHandler? = null

        /*
        start() method can be used to create all the game
        (suggest only to use once at the beginning)
         */

        fun toPlayCommand() {
            Viewer.renderPlay(stateModificationsHandler?.getGame(), stateModificationsHandler?.getAdditionalInfo())
        }

        private fun onLoseCommand() {
            forceInitializeGame()
            Viewer.renderStart()
            isFinished = false
        }

        fun startCommand() {
            initializeGame()
            Viewer.renderStart()
        }

        fun performEquipItemAction(entity: GameEntity<Equipment>) {
            Viewer.renderToInventory(
                stateModificationsHandler!!.performEquipItemAction(entity),
                stateModificationsHandler!!.getAdditionalInfo()
            )
        }

        fun performTakeOffItemAction(entity: GameEntity<Equipment>) {
            Viewer.renderToInventory(
                stateModificationsHandler!!.performTakeOffItemAction(entity),
                stateModificationsHandler!!.getAdditionalInfo()
            )
        }

        /*
        throwKeyboardInput() method can be used to parse clicks on Keyboard from user
        to InterfaceCommands
         */

        private val keyCodes = setOf(KeyCode.KEY_W, KeyCode.KEY_A, KeyCode.KEY_S, KeyCode.KEY_D, KeyCode.SPACE)

        fun throwKeyboardInput(input: KeyboardEvent, screen: Screen) {
            if (input.code in keyCodes) {
                renderStepCommand(screen, input)
            } else {
                print(input.key)
            }
        }

        fun onInventoryOpen() {
            isActive = false
        }

        fun onInventoryClose() {
            isActive = true
        }

        fun onGameSecondlyChange() {
            toPlayCommand()
        }

        fun onLose() {
            isActive = false
            isFinished = true
            onLoseCommand()
        }
        /*
        initializeGame() method is private and used to create ModificationHandler if not exists
        that mainly holds all the Game state
         */

        private fun initializeGame() {
            if (stateModificationsHandler == null) {
                forceInitializeGame()
            }
        }

        private fun forceInitializeGame() {
            stateModificationsHandler = Initializer.initialize(GameWorldBuilder.GENERATE, "", Difficulty.EASY)
        }
        /*
        interpret() method is private and used to respond to InterfaceCommands with
        proper modification of state and interface
         */

        private fun renderStepCommand(
            screen: Screen?,
            uiEvent: UIEvent?
        ) {
            Viewer.renderPlay(stateModificationsHandler?.updateCurrentGame(screen!!, uiEvent!!),
                stateModificationsHandler?.getAdditionalInfo())
        }
    }
}