package model.entity

import controller.GameContext
import controller.messages.GameMessage
import controller.messages.Pass
import controller.messages.Response
import model.entity.attributes.Attribute
import model.entity.behaviors.Behavior
import model.entity.facets.Facet
import model.entity.types.BaseType
import org.hexworks.cobalt.datatypes.Maybe
import kotlin.reflect.KClass

class GameEntity<T: BaseType>(
    val type: T,
    val attributes: MutableList<out Attribute>,
    val behaviors: MutableList<out Behavior<T>>,
    val facets: MutableList<out Facet<out GameMessage>>
) {

    inline fun <reified V : Attribute> findAttribute(klass: KClass<V>): Maybe<V>{
        for(attribute in attributes) {
            if(attribute is V){
                return Maybe.of(attribute)
            }
        }
        return Maybe.empty()
    }

    suspend fun receiveMessage(message: GameMessage): Response{
        var response: Response = Pass
        for(facet in facets) {
            var lastCommand = message
            if (response == Pass) {
                response = facet.tryReceive(lastCommand)
            }
        }
        return response
    }

    fun needsUpdate(): Boolean {
        return behaviors.isNotEmpty()
    }

    suspend fun update(context: GameContext): Boolean {
        return behaviors.fold(false) { result, behavior ->
            result or behavior.update(this, context)
        }
    }

}