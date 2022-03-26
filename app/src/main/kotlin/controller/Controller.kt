package controller

import model.StateModificationsHandler
import view.InterfaceCommands
import view.Viewer

class Controller {

    companion object {
        private var isFinished = false
        private var stateModificationsHandler : StateModificationsHandler? = null
        private var inValue : InterfaceCommands? = null

        fun throwInput(input: InterfaceCommands){
            inValue = input
            interpret(input)
        }

        private fun start(){
            stateModificationsHandler = Initializer.initialize(Initializer.GENERATE, "", GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT)
        }

        private fun interpret(input: InterfaceCommands){
            when(input){
                InterfaceCommands.START -> {
                    start()
                    Viewer.render(input)
                }
                InterfaceCommands.TO_PLAY -> {
                    Viewer.render(input, stateModificationsHandler?.getMap())
                }
                else -> {
                    return
                }
            }
        }
    }
}