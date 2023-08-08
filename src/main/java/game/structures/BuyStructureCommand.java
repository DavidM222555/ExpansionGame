package game.structures;

import game.Game;
import game.gui.SendTextToLogCommand;
import game.player.Player;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.uievent.KeyCode;

public class BuyStructureCommand {
    private static void setBlockAndStructureInteraction(Player player,
                                                        GameBlock selectedGameBlock, Structure structure) {
        selectedGameBlock.setStructure(structure);
        handleStructureCost(structure, player);
        selectedGameBlock.setTeam(player.getTeam());

        structure.setPos(selectedGameBlock.getPosition());
        structure.setTeam(player.getTeam());
        player.structureManager.addStructure(structure);
    }

    public static void execute(KeyCode kc, Game game, Player player) {
        var possibleStructure = StructureStore.getStructureFromKey(kc);

        possibleStructure.ifPresent(structure -> {
            var selectedGameBlock = game.getSelectedGameBlock();

            if (checkConditionsAndHandleLogging(selectedGameBlock, player,
                    structure)) {
                setBlockAndStructureInteraction(player, selectedGameBlock,
                        structure);
            }
        });
    }

    private static boolean haveEnoughForStructure(Structure structure,
                                                  Player player) {
        return (structure.getGoldCost() <= player.getGoldAmount() && structure.getIronCost() <= player.getIronAmount() && structure.getWoodCost() <= player.getWoodAmount());
    }

    private static void handleStructureCost(Structure structure,
                                            Player player) {
        player.resourceManager.changeResourceAmounts(-1 * structure.getGoldCost(), -1 * structure.getIronCost(), -1 * structure.getWoodCost());
    }

    private static boolean checkConditionsAndHandleLogging(GameBlock selectedGameBlock, Player player, Structure structure) {
        return selectedGameBlock != null && BuyStructureCommand.checkLegalResourceStructurePlacement(structure, selectedGameBlock) && BuyStructureCommand.checkStructureCost(structure, player) && BuyStructureCommand.checkBlockIsEmpty(selectedGameBlock) && checkIsPlayerControlled(selectedGameBlock, player) && checkIsPlayerMovable(selectedGameBlock);
    }

    private static boolean checkStructureCost(Structure structure,
                                              Player player) {
        if (!haveEnoughForStructure(structure, player)) {
            SendTextToLogCommand.execute("YOU DON'T HAVE ENOUGH " +
                    "RESOURCES" + " FOR THIS STRUCTURE! ");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkBlockIsEmpty(GameBlock selectedGameBlock) {
        if (!selectedGameBlock.doesntHaveStructureOrUnitOnIt()) {
            SendTextToLogCommand.execute("CAN'T PLACE STRUCTURE ON NON-EMPTY "
                    + "TILE");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkIsPlayerControlled(GameBlock selectedGameBlock, Player player) {
        if (selectedGameBlock.getTeam() == null || selectedGameBlock.getTeam() != player.getTeam()) {
            SendTextToLogCommand.execute("MUST PLACE STRUCTURE ON TILE YOU " + "CONTROL");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkIsPlayerMovable(GameBlock selectedGameBlock) {
        if (!selectedGameBlock.isPlayerMovable()) {
            SendTextToLogCommand.execute("CAN'T PLACE STRUCTURE ON THIS TYPE "
                    + "OF " + "TERRAIN");
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param structure Structure to test
     * @param gameBlock Block to possibly place structure on
     * @return Whether it is valid for a structure to be placed on a given tile.
     * Currently, it is mainly used for testing whether a resource miner is
     * on a tile with a resource on it and if so that that resource is
     * of the correct type.
     */
    private static boolean checkLegalResourceStructurePlacement(Structure structure, GameBlock gameBlock) {

        if (structure instanceof ResourceStructure) {
            if (!gameBlock.hasResourceOnIt()) {
                SendTextToLogCommand.execute("CAN'T PLACE RESOURCE " +
                        "STRUCTURES ON NON-RESOURCES");
                return false;
            }

            if (((ResourceStructure) structure).resourceType.equals(gameBlock.getResource().getResourceType().toString())) {
                return true;
            } else {
                SendTextToLogCommand.execute("CAN'T PLACE RESOURCE STRUCTURE "
                        + "ON NON-CORRESPONDING RESOURCE");
                return false;
            }

        }

        if (gameBlock.hasResourceOnIt()) {
            SendTextToLogCommand.execute("CAN'T PLACE NON-RESOURCE " +
                    "STRUCTURES ON RESOURCES");
            return false;
        }

        return true;
    }
}
