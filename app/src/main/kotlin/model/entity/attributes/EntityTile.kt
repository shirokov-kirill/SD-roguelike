package model.entity.attributes

import org.hexworks.zircon.api.data.Tile

/*
This is an Attribute class that provides
entity with its tile view
 */

data class EntityTile(val tile: Tile = Tile.empty()) : Attribute