package model.entity.attributes.effects

class Embarrassment(override var duration: Int): Effect {

    override fun clone(): Effect {
        return Embarrassment(duration)
    }

}