package model.entity

import view.views.play.resources.GameTiles
import controller.messages.GameMessage
import model.entity.attributes.*
import model.entity.behaviors.*
import model.entity.facets.*
import model.entity.types.*
import java.lang.Math.floor

/*
EntityFactory is a factory which
supplies game with entities it needs
 */

private fun <T : BaseType> newGameEntityOfType(
    type: T,
    attributes: MutableList<Attribute>) = GameEntity(type, attributes, mutableListOf(), mutableListOf())

private fun <T : Equipment> newEquipmentOfType(
    type: T,
    attributes: MutableList<Attribute>) = GameEntity(type, attributes, mutableListOf(), mutableListOf())

private fun <T : Creature> newAliveGameEntityOfType(
    type: T,
    attributes: MutableList<Attribute>,
    behaviors: MutableList<Behavior>,
    facets: MutableList<Facet<out GameMessage>>) = GameEntity(type, attributes, behaviors, facets)

object EntityFactory {

    fun getEmptyEntity() = newGameEntityOfType(
        Empty,
        mutableListOf(EntityPosition(), EntityTile())
    )

    fun getDefaultEquipment() = newEquipmentOfType(DefaultEquipment, mutableListOf(InventoryItemChars(0, 0))) as GameEntity<Equipment>

    fun createPlayer() = newAliveGameEntityOfType(
        Player,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.PLAYER), EntityLevel(), EntityInventory()),
        mutableListOf(InputHandler()),
        mutableListOf(Movable(), ViewMover(), Hitable(), CanEquipInventoryItems(), CanTakeOffInventoryItems()))

    fun createMonster() = createMonsterWithProbability()

    private fun createMonsterWithProbability(): GameEntity<Monster> {
        val res = Math.random()
        if(res < 0.33){
            return createAgressiveMonster()
        } else if(res < 0.66){
            return createScaredMonster()
        } else {
            return createStandingMonster()
        }
    }

    private fun createScaredMonster() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.SCARED_MONSTER), EntityLevel(), EntityInventory()),
        mutableListOf(ScaryMove()),
        mutableListOf(Movable(), Hitable())
    )

    private fun createStandingMonster() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.STANDING_MONSTER), EntityLevel(), EntityInventory()),
        mutableListOf(StandingMove()),
        mutableListOf(Movable(), Hitable())
    )

    private fun createAgressiveMonster() = newAliveGameEntityOfType(
        Monster,
        mutableListOf(EntityDirection(), EntityPosition(), EntityTile(GameTiles.AGRESSIVE_MONSTER), EntityLevel(), EntityInventory()),
        mutableListOf(AgressiveMove()),
        mutableListOf(Movable(), Hitable())
    )

    fun createEqiupment() = createEquipmentWithProbability()

    private fun createEquipmentWithProbability(): GameEntity<out Equipment> {
        val res = Math.random()
        if(res < 0.25){
            return createHeadEquipment()
        } else if(res < 0.5){
            return createBodyEquipment()
        } else if(res < 0.75){
            return createHandEquipment()
        } else {
            return createLegEquipment()
        }
    }

    private fun createHeadEquipment() = newEquipmentOfType(
        HeadEquipment,
        mutableListOf(EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = HEAD_EQUIPMENT[floor(Math.random() * (HEAD_EQUIPMENT.size - 1)).toInt()])),
    )

    private fun createBodyEquipment() = newEquipmentOfType(
        BodyEquipment,
        mutableListOf(EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = BODY_EQUIPMENT[floor(Math.random() * (BODY_EQUIPMENT.size - 1)).toInt()]))
    )

    private fun createHandEquipment() = newEquipmentOfType(
        HandEquipment,
        mutableListOf(EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = HAND_EQUIPMENT[floor(Math.random() * (HAND_EQUIPMENT.size - 1)).toInt()]))
    )

    private fun createLegEquipment() = newEquipmentOfType(
        LegEquipment,
        mutableListOf(EntityPosition(), EntityTile(GameTiles.DROPPED_EQUIPMENT), InventoryItemChars(initialName = LEG_EQUIPMENT[floor(Math.random() * (LEG_EQUIPMENT.size - 1)).toInt()]))
    )
}