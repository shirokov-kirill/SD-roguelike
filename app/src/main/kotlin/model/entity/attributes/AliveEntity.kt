package model.entity.attributes

import model.entity.GameEntity
import model.entity.attributes.effects.Effect
import model.entity.factory.EquipmentFactory
import model.entity.types.*
import org.hexworks.zircon.api.data.Position3D
import kotlin.reflect.KClass

/*
Declare get() and set() methods for inner
attributes of only alive entities
 */


/*
direction attribute
 */

var AliveEntity.direction
    get() = tryToFindAttribute(EntityDirection::class).direction
    set(value) {
        findAttribute(EntityDirection::class).map {
            it.direction = value
        }
    }

/*
targetPosition attribute
 */

fun AliveEntity.calculatePosition(): Position3D {
    val position = tryToFindAttribute(EntityPosition::class).position
    when(tryToFindAttribute(EntityDirection::class).direction) {
        Directions.LEFT -> {
            return position.withRelativeX(-1)
        }
        Directions.RIGHT -> {
            return position.withRelativeX(1)
        }
        Directions.TOP -> {
            return position.withRelativeY(-1)
        }
        Directions.BOTTOM -> {
            return position.withRelativeY(1)
        }
    }
}

val AliveEntity.targetPosition: Position3D
    get() = calculatePosition()

/*
Entity inventory management
 */

fun AliveEntity.equipItem(item: GameEntity<Equipment>): Boolean{
    val equippedItems = this.equippedItems
    if(this.inventory.contains(item)) {
        when(item.type) {
            HeadEquipment -> {
                this.equippedItems = equippedItems.set(0, item)
            }
            BodyEquipment -> {
                this.equippedItems = equippedItems.set(1, item)
            }
            LegEquipment -> {
                this.equippedItems = equippedItems.set(2, item)
            }
            HandEquipment -> {
                this.equippedItems = equippedItems.set(3, item)
            }
        }
        return true
    }
    return false
}

fun AliveEntity.takeOffItem(item: GameEntity<Equipment>): Boolean{
    val equippedItems = this.equippedItems
    if(this.equippedItems.contains(item)) {
        when(item.type) {
            HeadEquipment -> {
                this.equippedItems = equippedItems.set(0, EquipmentFactory().getDefault())
            }
            BodyEquipment -> {
                this.equippedItems = equippedItems.set(1, EquipmentFactory().getDefault())
            }
            LegEquipment -> {
                this.equippedItems = equippedItems.set(2, EquipmentFactory().getDefault())
            }
            HandEquipment -> {
                this.equippedItems = equippedItems.set(3, EquipmentFactory().getDefault())
            }
        }
        return true
    }
    return false
}

fun AliveEntity.addItemToInventory(item: GameEntity<Equipment>) {
    this.inventory = this.inventory.add(item)
}

var AliveEntity.equippedItems
    get() = tryToFindAttribute(EntityInventory::class).equippedItems
    set(value) {
        findAttribute(EntityInventory::class).map {
            it.equippedItems = value
        }
    }

var AliveEntity.inventory
    get() = tryToFindAttribute(EntityInventory::class).inventory
    set(value) {
        findAttribute(EntityInventory::class).map {
            it.inventory = value
        }
    }

/*
Deal with Effects
*/

fun AliveEntity.recalculateEffects() {
    var effects = this.effects
    val effectsToRemove = mutableListOf<Effect>()
    for (appliedEffect in effects) {
        appliedEffect.duration -= 1
        if(appliedEffect.duration == 0){
            effectsToRemove.add(appliedEffect)
        }
    }
    for (effect in effectsToRemove){
        effects = effects.remove(effect)
    }
    this.effects = effects
}

fun AliveEntity.applyEffect(effect: Effect) {
    var effects = this.effects
    var needToAdd = true
    for (appliedEffect in effects) {
        if(appliedEffect::class == effect::class){
            appliedEffect.duration += effect.duration
            needToAdd = false
        }
    }
    if(needToAdd){
        effects = effects.add(effect)
    }
    this.effects = effects
}

var AliveEntity.effects
    get() = tryToFindAttribute(Effects::class).effects
    set(value) {
        findAttribute(Effects::class).map {
            it.effects = value
        }
    }

/*
Deal with Entity Experience Stats
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

private fun AliveEntity.calculateTotalHitPoints(): Int {
    val baseHitPoints = tryToFindAttribute(EntityLevel::class).hitPoints
    var additionalHitPoints = 0
    val equippedItems = tryToFindAttribute(EntityInventory::class).equippedItems
    for (item in equippedItems) {
        additionalHitPoints += item.hitPointsBuff
    }
    return baseHitPoints + additionalHitPoints
}

fun AliveEntity.performHit(damage: Int, effects: Array<Effect>?) {
    var damageLeft = damage
    val equippedItems = this.equippedItems
    val itemsToDelete: MutableList<GameEntity<Equipment>> = mutableListOf()
    for(item in equippedItems) {
        if(item.hitPointsBuff > damageLeft) {
            item.hitPointsBuff = item.hitPointsBuff - damageLeft
            damageLeft = 0
            return
        }
        if(item.hitPointsBuff == damageLeft) {
            itemsToDelete.add(item)
            damageLeft = 0
            break
        }
        if(item.hitPointsBuff < damageLeft) {
            itemsToDelete.add(item)
            damageLeft -= item.hitPointsBuff
        }
    }
    if(damageLeft > 0){
        this.hitPoints -= damageLeft
    }
    val inventory = this.inventory
    for (item in itemsToDelete) {
        equippedItems.remove(item)
        inventory.remove(item)
    }
    if(effects != null){
        for (effect in effects) {
            this.applyEffect(effect)
        }
    }
}

var AliveEntity.hitPoints
    get() = calculateTotalHitPoints()
    set(value) {
        findAttribute(EntityLevel::class).map {
            it.hitPoints = value
        }
    }

private fun AliveEntity.calculateTotalDamage(): Int {
    val baseDamage = tryToFindAttribute(EntityLevel::class).damage
    var additionalDamage = 0
    val equippedItems = tryToFindAttribute(EntityInventory::class).equippedItems
    for (item in equippedItems) {
        additionalDamage += item.damageOnHit
    }
    return baseDamage + additionalDamage
}

var AliveEntity.damage
    get() = calculateTotalDamage()
    set(value) {
        findAttribute(EntityLevel::class).map {
            it.damage = value
        }
    }

inline fun <reified T : Attribute> AliveEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}


