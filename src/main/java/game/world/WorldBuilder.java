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

    public final JNoise ironNoiseGenerator =
            JNoise.newBuilder().worley(WorleyNoiseGenerator.newBuilder().setSeed(2500).build()).scale(1 / 16.0).clamp(0.0, 1.0).build();


    public final JNoise woodNoiseGenerator =
            JNoise.newBuilder().worley(WorleyNoiseGenerator.newBuilder().setSeed(1500).build()).scale(1 / 16.0).clamp(0.0, 1.0).build();


    private final Size3D worldSize;
    private final Map<Position3D, GameBlock> blocks = new HashMap<>();


    public WorldBuilder(Size3D worldSize) {
        this.worldSize = worldSize;
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


    private WorldBuilder initGoldOnTiles() {
        var posIterator = worldSize.fetchPositions().iterator();

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();
            var noiseValue =
                    goldNoiseGenerator.evaluateNoise(currentPos.getX() + 2.1,
                            currentPos.getY() + 2.1);

            if (noiseValue >= 0.60) {
                var currentBlock = blocks.get(currentPos);

                SetResourceOnTileCommand.execute(currentBlock,
                        ResourceStore.getResource(ResourceType.GOLD, 100));
            }
        }

        return this;
    }

    private WorldBuilder initIronOnTiles() {
        var posIterator = worldSize.fetchPositions().iterator();

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();
            var noiseValue =
                    ironNoiseGenerator.evaluateNoise(currentPos.getX() + 10,
                            currentPos.getY() + 10);

            if (noiseValue >= 0.55) {
                var currentBlock = blocks.get(currentPos);

                SetResourceOnTileCommand.execute(currentBlock,
                        ResourceStore.getResource(ResourceType.IRON, 100));
            }
        }

        return this;
    }

    private WorldBuilder initWoodOnTiles() {
        var posIterator = worldSize.fetchPositions().iterator();

        while (posIterator.hasNext()) {
            var currentPos = posIterator.next();
            var noiseValue =
                    woodNoiseGenerator.evaluateNoise(currentPos.getX() + 10,
                            currentPos.getY() + 10);

            if (noiseValue >= 0.55) {
                var currentBlock = blocks.get(currentPos);

                SetResourceOnTileCommand.execute(currentBlock,
                        ResourceStore.getResource(ResourceType.WOOD, 100));
            }
        }

        return this;
    }

    public WorldBuilder initResources() {
        return this.initGoldOnTiles().initIronOnTiles().initWoodOnTiles();
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
}
