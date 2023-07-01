package game.world.block;

import game.resources.Resource;
import game.structures.Structure;
import game.teams.Team;
import game.units.Unit;
import kotlin.Pair;
import org.hexworks.zircon.api.data.BlockTileType;
import org.hexworks.zircon.api.data.Tile;
import org.hexworks.zircon.api.data.base.BaseBlock;

import static game.world.block.TileStore.*;
import static kotlinx.collections.immutable.ExtensionsKt.persistentMapOf;


public class GameBlock extends BaseBlock<Tile> {
    Tile content;
    boolean selected;
    Structure structure;
    Unit unit;
    Resource resource;
    Team team;

    public GameBlock() {
        super(EMPTY_TILE, persistentMapOf(new Pair<>(BlockTileType.CONTENT,
                GROUND_TILE)));

        this.content = super.getContent();
        this.selected = false;

        this.structure = null;
        this.unit = null;
        this.resource = null;
    }

    public GameBlock(Tile content) {
        super(EMPTY_TILE, persistentMapOf(new Pair<>(BlockTileType.CONTENT,
                content)));
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void selectTile() {
        this.selected = true;

        System.out.println("Content of tile: " + this.content);
    }

    public boolean hasStructureOrUnitOnIt() {
        return structure != null && unit != null;
    }

    boolean isWater() {
        return content == WATER_TILE;
    }

    boolean isGround() {
        return content == GROUND_TILE;
    }
}
