package model.entity.attributes

import model.entity.GameEntity
import model.entity.types.BaseType
import model.entity.types.Creature
import model.entity.types.Equipment

typealias AnyEntity = GameEntity<out BaseType>
typealias AliveEntity = GameEntity<out Creature>
typealias InventoryItem = GameEntity<out Equipment>
