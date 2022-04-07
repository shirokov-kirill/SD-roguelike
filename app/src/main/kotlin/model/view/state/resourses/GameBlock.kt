package model.view.state.resourses

import controller.GameContext
import controller.messages.GameMessage
import controller.messages.Hit
import model.entity.GameEntity
import view.views.play.resources.GameTiles.EMPTY
import view.views.play.resources.GameTiles.FLOOR
import view.views.play.resources.GameTiles.WALL
import kotlinx.collections.immutable.persistentMapOf
import model.entity.EntityFactory
import model.entity.attributes.damage
import model.entity.attributes.tile
import model.entity.types.BaseType
import model.entity.types.Creature
import model.entity.types.Empty
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock
import view.views.play.resources.GameTiles.MONSTER
import view.views.play.resources.GameTiles.PLAYER
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.typeOf

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
        return isWall || currentEntity.isCreature()
    }

    fun hit(entity: GameEntity<out Creature>, context: GameContext) {
        if(!canHit()){
            return
        } else {
            if(isWall) {
                content = FLOOR
            } else {
                if(currentEntity.isCreature()){
                    currentEntity.receiveMessage(Hit(currentEntity as GameEntity<Creature>, entity, entity.damage, context))
                }
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
            entityTile == MONSTER -> MONSTER
            entityTile == WALL -> WALL
            else -> defaultTile
        }
    }
}