package game;

import game.player.Player;
import game.resources.ResourceType;
import game.structures.StructureStore;
import game.teams.Team;
import game.units.UnitStore;
import game.world.World;
import game.world.WorldBuilder;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.color.TileColor;
import org.hexworks.zircon.api.data.Position;
import org.hexworks.zircon.api.data.Size3D;

import java.io.IOException;
import java.util.Optional;

public class Game {
    private final UnitStore unitStore;
    private final StructureStore structureStore;
    private final World world;
    private final Player player;
    private final Team team;
    private GameBlock selectedGameBlock;

    public Game() throws IOException {
        this.world = buildWorld();
        this.player = new Player();
        this.team = new Team("Red team", TileColor.fromString("#B92743"));
        this.unitStore = UnitStore.fromJSONDirectory("src/main/assets/units");
        this.structureStore = StructureStore.fromJSONDirectory("src/main" +
                "/assets/structures");

    }

    public World buildWorld() {
        return new WorldBuilder(Size3D.create(GAME_CONSTANTS.GAME_X,
                GAME_CONSTANTS.GAME_Y, GAME_CONSTANTS.GAME_Z)).setInitialTiles().setResourcesOnTiles(ResourceType.GOLD, 65).refreshContentOfAllTiles().build(Size3D.create(GAME_CONSTANTS.VISIBLE_GAME_X, GAME_CONSTANTS.VISIBLE_GAME_Y, GAME_CONSTANTS.VISIBLE_GAME_Z));

    }

    public void tick() {
        this.player.update(this);
    }

    public World getWorld() {
        return world;
    }

    public Optional<GameBlock> getBlockAtPosOrNull(Position pos) {
        return Optional.ofNullable(this.world.fetchBlockAtOrNull(pos.toPosition3D(0)));
    }

    public GameBlock getSelectedGameBlock() {
        return selectedGameBlock;
    }

    public void setSelectedGameBlock(GameBlock gb) {
        this.selectedGameBlock = gb;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }

    public UnitStore getUnitStore() {
        return unitStore;
    }

    public StructureStore getStructureStore() {
        return structureStore;
    }

    public int getGameBoardWidth() {
        return this.world.getActualSize().getXLength();
    }

    public int getGameBoardHeight() {
        return this.world.getActualSize().getYLength();
    }
}
