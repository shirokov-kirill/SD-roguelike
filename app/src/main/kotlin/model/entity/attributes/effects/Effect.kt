package model.entity.attributes.effects

interface Effect {

    var duration: Int

    fun clone(): Effect

}