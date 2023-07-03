package game.units;

import game.Game;
import game.world.block.GameBlock;

public interface UnitMovementStrategy {
    GameBlock getMove(Game game, Unit unit);
}
