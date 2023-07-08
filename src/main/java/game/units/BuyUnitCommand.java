package game.units;

import game.Game;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.uievent.KeyCode;

public class BuyUnitCommand {
    public static void execute(KeyCode kc, Game game) {
        var possibleUnit = UnitStore.getUnitFromKey(kc);

        possibleUnit.ifPresent(unit -> {
            var selectedGameBlock = game.getSelectedGameBlock();

            // Make sure a block is selected and that it currently doesn't
            // have a structure or unit on it already.
            if (selectedGameBlock != null && !selectedGameBlock.hasStructureOrUnitOnIt() && selectedGameBlock.isPlayerMovable()) {
                setBlockAndUnitInteraction(game, selectedGameBlock, unit);
            }
        });
    }

    private static void setBlockAndUnitInteraction(Game game,
                                                   GameBlock selectedGameBlock, Unit unit) {
        MoveUnitCommand.executeWithKnownBlock(unit, selectedGameBlock);
        game.getPlayer().unitManager.addUnit(unit);
    }
}
