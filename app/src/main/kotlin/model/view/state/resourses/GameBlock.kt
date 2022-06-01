package model.view.state.resourses

import model.entity.GameEntity
import view.views.play.resources.GameTiles.EMPTY
import view.views.play.resources.GameTiles.FLOOR
import view.views.play.resources.GameTiles.WALL
import kotlinx.collections.immutable.persistentMapOf
import model.entity.EntityFactory
import model.entity.attributes.tile
import model.entity.types.BaseType
import model.entity.types.Empty
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock
import view.views.play.resources.GameTiles.PLAYER

class GameBlock(
    content: Tile = FLOOR,
    private var defaultTile: Tile = FLOOR,
    private var currentEntity: GameEntity<out BaseType> = EntityFactory.getEmptyEntity()
) : BaseBlock<Tile>(
    emptyTile = EMPTY,
    tiles = persistentMapOf(BlockTileType.CONTENT to content)
) {

    val isFloor: Boolean
        get() = content == FLOOR

    val isWall: Boolean
        get() = content == WALL

    val isEmptyBlock: Boolean
        get() = currentEntity.type == Empty && content == FLOOR

    val entity: GameEntity<out BaseType>
        get() = currentEntity

    fun addEntity(entity: GameEntity<out BaseType>){
        currentEntity = entity
        updateContent()
    }

    private fun canHit(): Boolean{
        return currentEntity.type != Empty || isWall
    }

    fun hit() {
        if(!canHit()){
            return
        } else {
            if(isWall) {
                content = FLOOR
            }
        }
    }

    fun removeEntity(){
        currentEntity = EntityFactory.getEmptyEntity()
        updateContent()
    }

    private fun updateContent() {
        val entityTile = currentEntity.tile
        content = when {
            entityTile == PLAYER -> PLAYER
            entityTile == WALL -> WALL
            else -> defaultTile
        }
    }
}