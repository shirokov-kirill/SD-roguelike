package model.entity.attributes.effects

import model.entity.facets.Cloneable

class Clonable(var capacity: Int, override var duration: Int = -1): Effect {

    var actual = 0

    fun updateValue(){
        actual = (actual + 1) % capacity
    }

    override fun clone(): Effect {
        return Clonable(capacity)
    }

}