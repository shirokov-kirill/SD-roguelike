package model.entity.attributes


import org.hexworks.zircon.api.data.Tile
import kotlin.reflect.KClass

/*
Declare get() and set() methods for inner
attributes of our entities
 */

var AnyEntity.position
    get() = tryToFindAttribute(EntityPosition::class).position
    set(value) {
        findAttribute(EntityPosition::class).map {
            it.position = value
        }
    }

var AnyEntity.direction
    get() = tryToFindAttribute(EntityDirection::class).direction
    set(value) {
        findAttribute(EntityDirection::class).map {
            it.direction = value
        }
    }

val AnyEntity.tile: Tile
    get() = this.tryToFindAttribute(EntityTile::class).tile

inline fun <reified T : Attribute> AnyEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}