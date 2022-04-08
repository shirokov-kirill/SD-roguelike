package model.entity.attributes

import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.api.property.Property
import kotlin.math.floor

class InventoryItemChars(damageBuff: Int? = null, healthBuff: Int? = null): Attribute {

    private val damageOnHitProperty: Property<Int>
    private var hitPointsBuffProperty: Property<Int>

    init {
        var damage = floor(Math.random() * 10).toInt() + 1
        if(damageBuff != null){
            damage = damageBuff
        }
        this.damageOnHitProperty = damage.toProperty()
        var health = floor(Math.random() * 10).toInt() + 1
        if(healthBuff != null) {
            health = healthBuff
        }
        this.hitPointsBuffProperty = health.toProperty()
    }

    val damageOnHit: Int by damageOnHitProperty.asDelegate()
    var hitPointsBuff: Int by hitPointsBuffProperty.asDelegate()

}