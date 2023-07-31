package game.world.block;

import game.resources.Resource;
import game.structures.Structure;
import game.teams.Team;
import game.units.Unit;
import kotlin.Pair;
import org.hexworks.zircon.api.color.TileColor;
import org.hexworks.zircon.api.data.BlockTileType;
import org.hexworks.zircon.api.data.Position;
import org.hexworks.zircon.api.data.Tile;
import org.hexworks.zircon.api.data.base.BaseBlock;

import java.util.Objects;

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

    // Represents whether a play is capable of going on this tile
    boolean playerMovable;
    boolean isVisible;

    Tile tile;

    public GameBlock() {
        super(EMPTY_TILE, persistentMapOf(new Pair<>(BlockTileType.CONTENT,
                GROUND_TILE)));

        this.structure = null;
        this.unit = null;
        this.resource = null;
        this.pos = null;
        this.isVisible = false;
    }

    public GameBlock(Tile content, Position pos, boolean playerMovable) {
        super(EMPTY_TILE, persistentMapOf(new Pair<>(BlockTileType.CONTENT,
                content)));

        this.pos = pos;
        this.playerMovable = playerMovable;
        this.isVisible = false;
        this.tile = content;
    }

    public Tile getTile() {
        return this.tile;
    }

    public Structure getStructure() {
        return this.structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
        this.refreshTileContent();
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
        this.refreshTileContent();
    }

    public void removeUnit() {
        this.unit = null;
        this.setContent(Objects.requireNonNull(this.getContent().asCharacterTileOrNull()).withCharacter(' '));

        this.refreshTileContent();
    }

    public Resource getResource() {
        return this.resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        this.refreshTileContent();
    }

    public void setTeam(Team team) {
        this.team = team;
        this.refreshTileContent();
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }


    public boolean doesntHaveStructureOrUnitOnIt() {
        return structure == null && unit == null;
    }

    /**
     * Refreshes the content of the tile to reflect structures, units,
     * and resources currently on it. Also, currently has some functionality
     * to handle the visibility of the tile.
     */
    public void refreshTileContent() {
        // Save the modifiers to add back at the end
        var currentModifiers = this.getContent().getModifiers();

        // Begin by handling current visibility of tiles relative to the player
        if (!this.isVisible) {
            this.setContent(Objects.requireNonNull(this.getContent().asCharacterTileOrNull()).withForegroundColor(TileColor.fromString("#000000")).withBackgroundColor(TileColor.fromString("#000000")));
            // If the tile is not visible then we can ignore everything else
            return;
        } else {
            this.setContent(this.getTile());
        }

        if (this.team != null) {
            this.setContent(Objects.requireNonNull(this.getContent().asCharacterTileOrNull()).withBackgroundColor(this.team.getTeamColor()));
        }

        // The way this is currently handled we have a precedence for each of
        // resource, structure, and unit, where structures and units are
        // guaranteed to never occur together on a given tile together.
        if (this.resource != null) {
            this.setContent(Objects.requireNonNull(this.getContent().asCharacterTileOrNull()).withCharacter(resource.getResourceChar()));
        }

        if (this.structure != null) {
            this.setContent(Objects.requireNonNull(this.getContent().asCharacterTileOrNull()).withCharacter(structure.getKey()));
        }

        if (this.unit != null) {
            this.setContent(Objects.requireNonNull(this.getContent().asCharacterTileOrNull()).withCharacter(unit.getKey()));
        }

        // Restore the previous modifiers that were on the tile
        this.setContent(this.getContent().withModifiers(currentModifiers));
    }

    public boolean hasResourceOnIt() {
        return this.resource != null;
    }

    public boolean isPlayerMovable() {
        return this.playerMovable;
    }
}
