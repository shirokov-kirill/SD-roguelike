package model.entity.attributes

import org.hexworks.cobalt.databinding.api.extension.toProperty

class EntityLevel(initialLevel: Int = 1, initialExperience: Int = 0): Attribute {

    private val levelProperty = initialLevel.toProperty()

    var level: Int by levelProperty.asDelegate()

    private val experienceProperty = initialExperience.toProperty()

    var expirience: Int by experienceProperty.asDelegate()

    private val hitPointsProperty = LEVEL_EXPERIENCE_TABLE[initialLevel][1].toProperty()

    var hitPoints: Int by hitPointsProperty.asDelegate()

    private val damageProperty = LEVEL_EXPERIENCE_TABLE[initialLevel][2].toProperty()

    var damage: Int by damageProperty.asDelegate()

}