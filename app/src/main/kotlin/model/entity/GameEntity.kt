package model.entity

import controller.GameContext
import controller.messages.GameMessage
import controller.messages.Pass
import controller.messages.Response
import model.entity.attributes.Attribute
import model.entity.behaviors.Behavior
import model.entity.facets.Facet
import model.entity.types.BaseType
import model.entity.types.Creature
import org.hexworks.cobalt.datatypes.Maybe
import kotlin.reflect.KClass

/*
GameEntity is a base class for any entity in the game
 */

class GameEntity<T: BaseType>(
    val type: T,
    val attributes: MutableList<out Attribute>,
    val behaviors: MutableList<Behavior<T>>,
    val facets: MutableList<out Facet<out GameMessage>>
) {

    /*
    findAttribute() is used to get access to specific attribute
    from the attributes list
    */

    inline fun <reified V : Attribute> findAttribute(klass: KClass<V>): Maybe<V>{
        for(attribute in attributes) {
            if(attribute is V){
                return Maybe.of(attribute)
            }
        }
        return Maybe.empty()
    }

    fun isCreature(): Boolean {
        return type is Creature
    }

    /*
    receiveMessage(message) is used to perform some action on an entity
    "from outside" and return a sufficient Response
    */

    fun receiveMessage(message: GameMessage): Response{
        var response: Response = Pass
        for(facet in facets) {
            var lastCommand = message
            if (response == Pass) {
                response = facet.tryReceive(lastCommand)
            }
        }
        return response
    }

    /*
    needsUpdate() is used to check if this entity
    do possibly need to be updated
    */

    fun needsUpdate(): Boolean {
        return behaviors.isNotEmpty()
    }

    /*
    update(message) is used to perform some action on an entity
    "from inside", generate Message("from outside behavior") and return a sufficient Response.
    */

    fun update(context: GameContext): Boolean {
        return behaviors.fold(false) { result, behavior ->
            result or behavior.update(this, context)
        }
    }

}