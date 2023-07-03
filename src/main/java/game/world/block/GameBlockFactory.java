package game.world.block;

import org.hexworks.zircon.api.data.Position;

public class GameBlockFactory {
    public static GameBlock ground(Position pos) {
        return new GameBlock(TileStore.GROUND_TILE, pos);
    }

    public static GameBlock water(Position pos) {
        return new GameBlock(TileStore.WATER_TILE, pos);
    }
}
