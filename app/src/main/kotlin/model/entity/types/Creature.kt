package model.entity.types

/*
Creature is a base type of any alive entity
 */

open class Creature(name: String, private val maxHitPoints: Int): BaseType(name) {

    private var hitPoints = 0

    init {
        hitPoints = maxHitPoints
    }

    fun getHitPoints(): Int {
        return hitPoints
    }

    fun restoreHealth() {
        hitPoints = maxHitPoints
    }
}