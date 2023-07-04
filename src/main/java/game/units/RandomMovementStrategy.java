package game.units;

import game.Game;
import game.world.block.GameBlock;

import java.util.Optional;
import java.util.Random;

public class RandomMovementStrategy {
    public static Optional<GameBlock> getMove(Game game, Unit unit) {
        var rng = new Random();
        var validMoves = MovementHelper.getValidMoveBlocks(unit, game);

        if (validMoves.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(validMoves.get(rng.nextInt(validMoves.size())));
    }

}
