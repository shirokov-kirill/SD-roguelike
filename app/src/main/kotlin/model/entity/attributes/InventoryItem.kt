package model.entity.attributes

import kotlin.reflect.KClass

var InventoryItem.hitPointsBuff
    get() = tryToFindAttribute(InventoryItemChars::class).hitPointsBuff
    set(value) {
        findAttribute(InventoryItemChars::class).map {
            it.hitPointsBuff = value
        }
    }

val InventoryItem.damageOnHit
    get() = tryToFindAttribute(InventoryItemChars::class).damageOnHit

val InventoryItem.name
    get() = tryToFindAttribute(InventoryItemChars::class).name

inline fun <reified T : Attribute> InventoryItem.tryToFindAttribute(klass: KClass<T>): T =
    findAttribute(klass).orElseThrow {
        NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
    }