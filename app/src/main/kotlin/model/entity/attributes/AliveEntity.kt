package model.entity.attributes

import controller.messages.EquipItem
import model.entity.EntityFactory
import model.entity.GameEntity
import model.entity.types.*
import org.hexworks.zircon.api.data.Position3D
import kotlin.reflect.KClass

/*
Declare get() and set() methods for inner
attributes of only alive entities
 */

var AliveEntity.direction
    get() = tryToFindAttribute(EntityDirection::class).direction
    set(value) {
        findAttribute(EntityDirection::class).map {
            it.direction = value
        }
    }

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
                this.equippedItems = equippedItems.set(0, EntityFactory.getDefaultEquipment())
            }
            BodyEquipment -> {
                this.equippedItems = equippedItems.set(1, EntityFactory.getDefaultEquipment())
            }
            LegEquipment -> {
                this.equippedItems = equippedItems.set(2, EntityFactory.getDefaultEquipment())
            }
            HandEquipment -> {
                this.equippedItems = equippedItems.set(3, EntityFactory.getDefaultEquipment())
            }
        }
        return true
    }
    return false
}

fun AliveEntity.addItemToInventory(item: GameEntity<Equipment>) {
    this.inventory = this.inventory.add(item)
}

var AliveEntity.level
    get() = tryToFindAttribute(EntityLevel::class).level
    set(value) {
        findAttribute(EntityLevel::class).map {
            it.level = value
        }
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

fun AliveEntity.performHit(damage: Int) {
    var damageLeft = damage
    val equippedItems = tryToFindAttribute(EntityInventory::class).equippedItems
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


