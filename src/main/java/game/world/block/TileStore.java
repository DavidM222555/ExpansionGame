package game.world.block;

import org.hexworks.zircon.api.color.TileColor;
import org.hexworks.zircon.api.data.CharacterTile;
import org.hexworks.zircon.api.data.Tile;

public class TileStore {
    public static final CharacterTile EMPTY_TILE = Tile.empty();

    public static final CharacterTile WATER_TILE =
            Tile.newBuilder().withBackgroundColor(TileColor.fromString(
                    "#48C9AB")).withCharacter('~').buildCharacterTile();

    public static final CharacterTile GROUND_TILE =
            Tile.newBuilder().withBackgroundColor(TileColor.fromString(
                    "#a8908d")).buildCharacterTile();

}
