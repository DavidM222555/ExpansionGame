package game.world;

import de.articdive.jnoise.generators.noisegen.worley.WorleyNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import game.resources.ResourceStore;
import game.resources.ResourceType;
import game.resources.SetResourceOnTileCommand;
import game.world.block.GameBlock;
import game.world.block.GameBlockFactory;
import org.hexworks.zircon.api.data.Position3D;
import org.hexworks.zircon.api.data.Size3D;

import java.util.HashMap;
import java.util.Map;

public class WorldBuilder {
    public final JNoise waterNoiseGenerator =
            JNoise.newBuilder().worley(WorleyNoiseGenerator.newBuilder().setSeed(1077).build()).scale(1 / 16.0).clamp(0.0, 1.0).build();


    public final JNoise goldNoiseGenerator =
            JNoise.newBuilder().worley(WorleyNoiseGenerator.newBuilder().setSeed(200).build()).scale(1 / 16.0).clamp(0.0, 1.0).build();


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

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();
            var noiseValue =
                    waterNoiseGenerator.evaluateNoise(currentPos.getX(),
                            currentPos.getY());

            if (noiseValue >= 0.4) {
                blocks.put(currentPos,
                        GameBlockFactory.water(currentPos.to2DPosition()));
            } else {
                blocks.put(currentPos,
                        GameBlockFactory.ground(currentPos.to2DPosition()));
            }
        }

        return this;
    }


    public WorldBuilder setResourcesOnTiles(ResourceType resourceType,
                                            int freq) {
        var posIterator = worldSize.fetchPositions().iterator();

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();
            var noiseValue =
                    goldNoiseGenerator.evaluateNoise(currentPos.getX() + 2.1,
                            currentPos.getY() + 2.1);

            if (noiseValue >= 0.65) {
                var currentBlock = blocks.get(currentPos);

                SetResourceOnTileCommand.execute(currentBlock,
                        ResourceStore.getResource(resourceType, 100));
            }
        }

        return this;
    }

    public WorldBuilder refreshContentOfAllTiles() {
        var posIterator = worldSize.fetchPositions().iterator();

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();

            var currentBlock = blocks.get(currentPos);
            currentBlock.refreshTileContent();
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
