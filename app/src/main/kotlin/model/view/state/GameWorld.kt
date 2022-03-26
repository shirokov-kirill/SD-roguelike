package model.view.state

import view.views.play.resources.GameBlock
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.GameArea

class GameWorld(
    startingBlocks: Map<Position3D, GameBlock>,         // 1
    visibleSize: Size3D,
    actualSize: Size3D
) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>() // 2
    .withVisibleSize(visibleSize)                       // 3
    .withActualSize(actualSize)                         // 4
    .build() {

    init {
        startingBlocks.forEach { (pos, block) ->
            setBlockAt(pos, block)                      // 5
        }
    }
}