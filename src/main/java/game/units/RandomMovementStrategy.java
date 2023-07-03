package game.units;

import game.Game;
import game.world.block.GameBlock;

import java.util.Random;

public class RandomMovementStrategy implements UnitMovementStrategy {
    public GameBlock getMove(Game game, Unit unit) {
        var rng = new Random();
        var validMoves = MovementHelper.getValidMoveBlocks(unit, game);

        var randomMoveBlock = validMoves.get(rng.nextInt(validMoves.size()));

        return randomMoveBlock;
    }

}
