package game;

import game.input.InputHandler;
import game.player.Player;
import game.units.UnitStore;
import game.world.World;
import game.world.WorldBuilder;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.data.Size3D;

import java.io.IOException;

public class Game {
    public World world;
    public InputHandler inputHandler;
    public GameBlock selectedGameBlock;
    public Player player;
    public UnitStore unitStore;


    public Game() throws IOException {
        this.world = new WorldBuilder(Size3D.create(GAME_CONSTANTS.GAME_X,
                GAME_CONSTANTS.GAME_Y, GAME_CONSTANTS.GAME_Z)).setInitialTiles().build(Size3D.create(GAME_CONSTANTS.VISIBLE_GAME_X, GAME_CONSTANTS.VISIBLE_GAME_Y, GAME_CONSTANTS.VISIBLE_GAME_Z));
        this.inputHandler = new InputHandler(this);
        this.player = new Player();
        this.unitStore = UnitStore.fromJSONDirectory("src/main/assets/units");
    }

    public void setSelectedGameBlock(GameBlock gb) {
        this.selectedGameBlock = gb;
    }
}
