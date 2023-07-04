package game.units;

import game.Game;
import game.world.block.GameBlock;

public class MoveUnitCommand {
    public static void executeWithKnownBlock(Unit unit, GameBlock gameBlock) {
        // If the unit is already on a block then remove it from that block
        if (unit.blockUnitIsOn != null) {
            unit.blockUnitIsOn.removeUnit();
        }

        gameBlock.setUnit(unit);
        unit.setBlockUnitIsOn(gameBlock);
        unit.setPos(gameBlock.getPosition());
    }

    public static void executeWithStrategy(Game game, Unit unit) {
        var blockFromStrategy = RandomMovementStrategy.getMove(game, unit);

        blockFromStrategy.ifPresent(gameBlock -> executeWithKnownBlock(unit,
                gameBlock));
    }
}
