package game.world.block;

import game.resources.Resource;
import game.structures.Structure;
import game.teams.Team;
import game.units.Unit;
import kotlin.Pair;
import org.hexworks.zircon.api.data.BlockTileType;
import org.hexworks.zircon.api.data.Position;
import org.hexworks.zircon.api.data.Tile;
import org.hexworks.zircon.api.data.base.BaseBlock;

import static game.world.block.TileStore.EMPTY_TILE;
import static game.world.block.TileStore.GROUND_TILE;
import static kotlinx.collections.immutable.ExtensionsKt.persistentMapOf;

@SuppressWarnings("unused")
public class GameBlock extends BaseBlock<Tile> {
    Structure structure;
    Unit unit;
    Resource resource;
    Team team;
    Position pos;

    public GameBlock() {
        super(EMPTY_TILE, persistentMapOf(new Pair<>(BlockTileType.CONTENT,
                GROUND_TILE)));

        this.structure = null;
        this.unit = null;
        this.resource = null;
        this.pos = null;
    }

    public GameBlock(Tile content, Position pos) {
        super(EMPTY_TILE, persistentMapOf(new Pair<>(BlockTileType.CONTENT,
                content)));

        this.pos = pos;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
        this.setContent(this.getContent().asCharacterTileOrNull().withCharacter(unit.getKey()));
    }

    public void removeUnit() {
        this.unit = null;
        this.setContent(this.getContent().asCharacterTileOrNull().withCharacter(' '));
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return this.pos;
    }

    public boolean hasStructureOrUnitOnIt() {
        return structure != null || unit != null;
    }

}
