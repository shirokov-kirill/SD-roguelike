package model.view.state

import controller.Controller
import controller.GameContext
import kotlinx.coroutines.*
import model.entity.GameEntity
import model.entity.types.BaseType
import model.entity.types.Creature
import model.entity.types.Player
import kotlin.coroutines.CoroutineContext

class GameEngine(
    private val world: GameWorld,
    override val coroutineContext: CoroutineContext = Dispatchers.Default
) : CoroutineScope {

    private var mainJob: Job
    private var player: GameEntity<Player>? = null
    private val entities: MutableList<GameEntity<out BaseType>> = mutableListOf()

    init {
        this.mainJob = launch {
            while(true) {
                if(Controller.isActive){
                    executeGameTurn()
                }
                delay(1000L)
            }
        }
    }

    private fun executeGameTurn() {
        entities.filter { it.type is Creature } .filter { it.needsUpdate() && it.type != Player }.map { it.update(GameContext(this.world, null, null, this.player)) }
        Controller.onGameSecondlyChange()
    }

    fun addEntity(entity: GameEntity<out BaseType>){
        entities.add(entity)
    }

    fun removeEntity(entity: GameEntity<out BaseType>){
        entities.remove(entity)
    }

    fun executeTurn(context: GameContext) {
        this.player = context.player
        entities.filter { it.needsUpdate() && it.type == Player }.map { it.update(context) }
    }
}