package game.world;

import game.resources.ResourceStore;
import game.resources.ResourceType;
import game.world.block.GameBlock;
import game.world.block.GameBlockFactory;
import game.world.noise.CellularAutomaton;
import org.hexworks.zircon.api.data.Position3D;
import org.hexworks.zircon.api.data.Size3D;

import java.util.HashMap;
import java.util.Map;

public class WorldBuilder {
    private final int width;
    private final int height;
    private final Size3D worldSize;
    private final Map<Position3D, GameBlock> blocks = new HashMap<>();

    public WorldBuilder(Size3D worldSize) {
        this.worldSize = worldSize;

        width = worldSize.getXLength();
        height = worldSize.getYLength();
    }

    public World build(Size3D visibleSize) {
        return new World(blocks, visibleSize, worldSize);
    }

    public WorldBuilder setInitialTiles() {
        var posIterator = worldSize.fetchPositions().iterator();
        var noiseGrid =
                CellularAutomaton.generateCellularAutomatonGrid(this.width,
                        this.height, 55, 4, 7);

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();

            if (noiseGrid.get(currentPos.getY()).get(currentPos.getX())) {
                blocks.put(currentPos,
                        GameBlockFactory.water(currentPos.to2DPosition()));
            } else {
                blocks.put(currentPos,
                        GameBlockFactory.ground(currentPos.to2DPosition()));
            }
        }

        return this;
    }

    
    public WorldBuilder setResourceOnTiles(ResourceType resourceType,
                                           int freq) {
        var posIterator = worldSize.fetchPositions().iterator();
        var noiseGrid =
                CellularAutomaton.generateCellularAutomatonGrid(this.width,
                        this.height, freq, 4, 7);

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();

            if (noiseGrid.get(currentPos.getY()).get(currentPos.getX())) {
                var currentBlock = blocks.get(currentPos);

                if (!currentBlock.hasResourceOnIt() && currentBlock.isPlayerMovable()) {
                    System.out.println("Setting resource");
                    currentBlock.setResource(ResourceStore.getResource(resourceType, 100));
                }
            }
        }

        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
