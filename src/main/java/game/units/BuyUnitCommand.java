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
            var selectedGameBlock = game.getSelectedGameBlock();

            if (BuyUnitCommand.checkConditionsAndHandleLogging(selectedGameBlock, player, unit)) {
                setBlockAndUnitInteraction(player, selectedGameBlock, unit);
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

    private static boolean checkConditionsAndHandleLogging(GameBlock selectedGameBlock, Player player, Unit unit) {
        return selectedGameBlock != null && BuyUnitCommand.checkUnitCost(unit
                , player) && BuyUnitCommand.checkBlockIsEmpty(selectedGameBlock) && checkIsPlayerControlled(selectedGameBlock, player) && checkIsPlayerMovable(selectedGameBlock);
    }

    private static boolean checkUnitCost(Unit unit, Player player) {
        if (!haveEnoughForUnit(unit, player)) {
            SendTextToLogCommand.execute("YOU DON'T HAVE ENOUGH " +
                    "RESOURCES" + " FOR THIS UNIT! ");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkBlockIsEmpty(GameBlock selectedGameBlock) {
        if (!selectedGameBlock.doesntHaveStructureOrUnitOnIt()) {
            SendTextToLogCommand.execute("CAN'T PLACE UNIT ON NON-EMPTY TILE");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkIsPlayerControlled(GameBlock selectedGameBlock, Player player) {
        if (selectedGameBlock.getTeam() == null || selectedGameBlock.getTeam() != player.getTeam()) {
            SendTextToLogCommand.execute("MUST PLACE UNIT ON TILE YOU CONTROL");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkIsPlayerMovable(GameBlock selectedGameBlock) {
        if (!selectedGameBlock.isPlayerMovable()) {
            SendTextToLogCommand.execute("CAN'T PLACE UNIT ON THIS TYPE OF " + "TERRAIN");
            return false;
        } else {
            return true;
        }
    }
}
