package model.entity.attributes

import kotlin.reflect.KClass

/*
Declare get() and set() methods for inner
attributes of only alive entities
 */

var AliveEntity.level
    get() = tryToFindAttribute(EntityLevel::class).level
    set(value) {
        findAttribute(EntityLevel::class).map {
            it.level = value
        }
    }

var AliveEntity.experience
    get() = tryToFindAttribute(EntityLevel::class).expirience
    set(value) {
        findAttribute(EntityLevel::class).map {
            it.expirience = value
        }
    }

var AliveEntity.hitPoints
    get() = tryToFindAttribute(EntityLevel::class).hitPoints
    set(value) {
        findAttribute(EntityLevel::class).map {
            it.hitPoints = value
        }
    }

val AliveEntity.damage
    get() = tryToFindAttribute(EntityLevel::class).damage

inline fun <reified T : Attribute> AliveEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}


