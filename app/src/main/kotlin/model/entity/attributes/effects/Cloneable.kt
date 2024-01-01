package model.entity.attributes.effects

class Cloneable(var capacity: Int, override var duration: Int = -1) : Effect {

    var actual = 0

    fun updateValue() {
        actual = (actual + 1) % capacity
    }

    override fun clone(): Effect {
        return Cloneable(capacity)
    }

}