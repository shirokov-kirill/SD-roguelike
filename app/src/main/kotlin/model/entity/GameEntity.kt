package model.entity

import com.google.gson.Gson
import controller.GameContext
import controller.messages.Clone
import controller.messages.GameMessage
import controller.messages.Pass
import controller.messages.Response
import model.entity.attributes.Attribute
import model.entity.behaviors.Behavior
import model.entity.facets.Facet
import model.entity.types.BaseType
import model.entity.types.Creature
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.behavior.Copiable
import kotlin.reflect.KClass

/*
GameEntity is a base class for any entity in the game
 */

open class GameEntity<T: BaseType>(
    open val type: T,
    open val attributes: MutableList<out Attribute>,
    val behaviors: MutableList<Behavior>,
    val facets: MutableList<out Facet<out GameMessage>>
) {

    /*
    findAttribute() is used to get access to specific attribute
    from the attributes list
    */

    inline fun <reified V : Attribute> findAttribute(klass: KClass<V>): Maybe<V> {
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

    fun receiveMessage(message: GameMessage): Response {
        if(isCreature()){
            var response: Response = Pass
            for(facet in facets) {
                var lastCommand = message
                if (response == Pass) {
                    response = facet.tryReceive(lastCommand)
                }
            }
            return response
        }
        return Pass
    }

    /*
    needsUpdate() is used to check if this entity
    do possibly need to be updated
    */

    fun needsUpdate(): Boolean {
        return isCreature() && behaviors.isNotEmpty()
    }

    /*
    update(message) is used to perform some action on an entity
    "from inside", generate Message("from outside behavior") and return a sufficient Response.
    */

    fun update(context: GameContext): Boolean {
        if(isCreature()){
            return behaviors.fold(false) { result, behavior ->
                result or behavior.update(this as GameEntity<Creature>, context)
            }
        }
        return false
    }

    fun deepCopy(): GameEntity<T> {
        val attributesCopy = attributes.map { attribute -> attribute.clone() }.toMutableList()
        return GameEntity(type, attributesCopy, behaviors, facets)
    }
}