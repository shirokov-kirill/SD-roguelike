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

    @Synchronized
    fun executeTurn(context: GameContext): Job {
        return launch {
            entities.filter { it.needsUpdate() }.map {
                async { it.update(context) }
            }.awaitAll()
        }
    }

}