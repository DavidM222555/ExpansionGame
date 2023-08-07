package game.units;

import game.Game;
import game.gui.SendTextToLogCommand;
import game.player.Player;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.uievent.KeyCode;

public class BuyUnitCommand {
    public static void execute(KeyCode kc, Game game, Player player) {
        var possibleUnit = UnitStore.getUnitFromKeyCode(kc);

        possibleUnit.ifPresent(unit -> {
            if (haveEnoughForUnit(unit, player)) {
                var selectedGameBlock = game.getSelectedGameBlock();

                // Make sure a block is selected and that it currently doesn't
                // have a structure or unit on it already.
                if (selectedGameBlock != null && selectedGameBlock.doesntHaveStructureOrUnitOnIt() && selectedGameBlock.isPlayerMovable()) {
                    setBlockAndUnitInteraction(player, selectedGameBlock, unit);
                } else {
                    SendTextToLogCommand.execute("CAN'T PLACE UNIT ON THIS " + "TILE!");
                }
            } else {
                SendTextToLogCommand.execute("YOU DON'T HAVE ENOUGH " +
                        "RESOURCES" + " FOR THIS UNIT! ");
            }

        });
    }

    private static void setBlockAndUnitInteraction(Player player,
                                                   GameBlock selectedGameBlock, Unit unit) {
        unit.setTeam(player.getTeam());
        MoveUnitCommand.executeWithKnownBlock(unit, selectedGameBlock);
        player.addUnit(unit);
        handleUnitCost(unit, player);
    }

    private static boolean haveEnoughForUnit(Unit unit, Player player) {
        return (unit.getGoldCost() <= player.getGoldAmount() && unit.getIronCost() <= player.getIronAmount() && unit.getWoodCost() <= player.getWoodAmount());
    }

    private static void handleUnitCost(Unit unit, Player player) {
        player.resourceManager.changeResourceAmounts(-1 * unit.getGoldCost(),
                -1 * unit.getIronCost(), -1 * unit.getWoodCost());
    }
}
