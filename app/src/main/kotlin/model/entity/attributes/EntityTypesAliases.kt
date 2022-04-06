package model.entity.attributes

import model.entity.GameEntity
import model.entity.types.BaseType
import model.entity.types.Creature

typealias AnyEntity = GameEntity<out BaseType>
typealias AliveEntity = GameEntity<out Creature>
