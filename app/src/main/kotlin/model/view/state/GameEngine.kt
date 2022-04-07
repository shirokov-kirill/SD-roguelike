package model.view.state

import controller.GameContext
import kotlinx.coroutines.*
import model.entity.GameEntity
import model.entity.types.BaseType
import kotlin.coroutines.CoroutineContext

class GameEngine(
    override val coroutineContext: CoroutineContext = Dispatchers.Default
) : CoroutineScope {

    private val entities: MutableList<GameEntity<out BaseType>> = mutableListOf()

    fun addEntity(entity: GameEntity<out BaseType>){
        entities.add(entity)
    }

    fun removeEntity(entity: GameEntity<out BaseType>){
        entities.remove(entity)
    }

    fun executeTurn(context: GameContext) {
        entities.filter { it.needsUpdate() }.map { it.update(context) }
    }
}