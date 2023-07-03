package game.units;

import game.Game;
import game.world.block.GameBlock;

public class MoveUnitCommand {
    public static void executeWithKnownBlock(Game game, Unit unit,
                                             GameBlock gameBlock) {
        gameBlock.setUnit(unit);
        unit.setBlockUnitIsOn(gameBlock);
        unit.setPos(gameBlock.getPosition());
        game.getPlayer().unitManager.addUnit(unit);
    }

    public static void executeWithStrategy(Game game, Unit unit,
                                           UnitMovementStrategy movementStrategy) {
        var blockFromStrategy = movementStrategy.getMove(game, unit);

        executeWithKnownBlock(game, unit, blockFromStrategy);
    }
}
