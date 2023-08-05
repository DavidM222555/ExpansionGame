package game.units;

import game.Game;
import game.gui.SendTextToLogCommand;
import game.player.Player;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.uievent.KeyCode;

public class BuyUnitCommand {
    public static void execute(KeyCode kc, Game game) {
        var possibleUnit = UnitStore.getUnitFromKeyCode(kc);

        possibleUnit.ifPresent(unit -> {
            if (haveEnoughForUnit(unit, game.getPlayer())) {
                var selectedGameBlock = game.getSelectedGameBlock();

                // Make sure a block is selected and that it currently doesn't
                // have a structure or unit on it already.
                if (selectedGameBlock != null && selectedGameBlock.doesntHaveStructureOrUnitOnIt() && selectedGameBlock.isPlayerMovable()) {
                    setBlockAndUnitInteraction(game, selectedGameBlock, unit);
                } else {
                    SendTextToLogCommand.execute("CAN'T PLACE UNIT ON THIS " +
                            "TILE!");
                }
            } else {
                SendTextToLogCommand.execute("YOU DON'T HAVE ENOUGH " +
                        "RESOURCES" + " FOR THIS UNIT! ");
            }

        });
    }

    private static void setBlockAndUnitInteraction(Game game,
                                                   GameBlock selectedGameBlock, Unit unit) {
        MoveUnitCommand.executeWithKnownBlock(unit, selectedGameBlock);
        game.getPlayer().addUnit(unit);
        handleUnitCost(unit, game.getPlayer());
    }

    private static boolean haveEnoughForUnit(Unit unit, Player player) {
        return (unit.getGoldCost() <= player.getGoldAmount() && unit.getIronCost() <= player.getIronAmount() && unit.getWoodCost() <= player.getWoodAmount());
    }

    private static void handleUnitCost(Unit unit, Player player) {
        player.resourceManager.changeResourceAmounts(-1 * unit.getGoldCost(),
                -1 * unit.getIronCost(), -1 * unit.getWoodCost());
    }
}
