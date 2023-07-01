package game.world

import game.world.block.GameBlock
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.GameArea

// Initial code taken from the following tutorial: https://hexworks.org/posts/tutorials/2019/01/05/how-to-make-a-roguelike-generating-random-caves.html
class World(
    startingBlocks: Map<Position3D, GameBlock>,
    visibleSize: Size3D,
    actualSize: Size3D
) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
    .withVisibleSize(visibleSize)
    .withActualSize(actualSize)
    .build() {

    init {
        startingBlocks.forEach { (pos, block) ->
            setBlockAt(pos, block)
        }
    }

    fun getBlock(pos: Position): GameBlock? {
        return this.fetchBlockAtOrNull(pos.toPosition3D(0))
    }
}