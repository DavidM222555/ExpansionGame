package game.world.block;

public class GameBlockFactory {
    public static GameBlock ground() {
        return new GameBlock(TileStore.GROUND_TILE);
    }

    public static GameBlock water() {
        return new GameBlock(TileStore.WATER_TILE);
    }
}
