package model.entity.attributes

import kotlin.reflect.KClass

/*
Declare get() and set() methods for inner
attributes of only alive entities
 */

fun AliveEntity.updateStats(){
    val requiredExperience = LEVEL_EXPERIENCE_TABLE[this.level][0]
    if(requiredExperience <= this.experience && this.level < LEVEL_EXPERIENCE_TABLE.size) {
        this.experience = this.experience % requiredExperience
        this.level++
        this.hitPoints = LEVEL_EXPERIENCE_TABLE[level][1]
        this.damage = LEVEL_EXPERIENCE_TABLE[level][2]
    } else {
        return
    }
}

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

var AliveEntity.damage
    get() = tryToFindAttribute(EntityLevel::class).damage
    set(value) {
        findAttribute(EntityLevel::class).map {
            it.damage = value
        }
    }

inline fun <reified T : Attribute> AliveEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}


