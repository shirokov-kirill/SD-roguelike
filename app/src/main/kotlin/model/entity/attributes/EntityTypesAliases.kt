package model.entity.attributes

// put this in a file called TypeAliases.kt

import controller.GameContext
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

typealias AnyEntity = GameEntity<EntityType>

typealias GameEntity<T> = Entity<T, GameContext>