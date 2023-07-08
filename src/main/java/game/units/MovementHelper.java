package game.units;

import game.Game;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.data.Position;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MovementHelper {
    static Position[] moveIncrements = {Position.create(1, 0),
            Position.create(-1, 0), Position.create(0, 1), Position.create(0,
            -1)};


    @NotNull
    @Contract(pure = true)
    public static List<GameBlock> getValidMoveBlocks(Unit unit, Game game) {
        List<GameBlock> validMoves = new ArrayList<>();
        var unitPos = unit.getPos();

        for (var move : moveIncrements) {
            var movedPos = move.plus(unitPos);

            // First check that the position would even have a game block on it
            if (moveInBounds(movedPos, game)) {
                var gameBlockOnMovedPos = game.getBlockAtPosOrNull(movedPos);

                if (gameBlockOnMovedPos.isPresent() && !gameBlockOnMovedPos.get().hasStructureOrUnitOnIt() && gameBlockOnMovedPos.get().isPlayerMovable()) {
                    validMoves.add(gameBlockOnMovedPos.get());
                }
            }
        }

        return validMoves;
    }

    private static boolean moveInBounds(Position pos, Game game) {
        return pos.getX() >= 0 && pos.getX() < game.getWorld().getActualSize().getXLength() && pos.getY() >= 0 && pos.getY() < game.getWorld().getActualSize().getYLength();
    }
}
