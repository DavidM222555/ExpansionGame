package game.structures;

import game.Game;
import game.player.Player;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.uievent.KeyCode;

public class BuyStructureCommand {
    private static void setBlockAndStructureInteraction(Game game,
                                                        GameBlock selectedGameBlock, Structure structure) {
        selectedGameBlock.setStructure(structure);
        handleStructureCost(structure, game.getPlayer());
        selectedGameBlock.setTeam(game.getTeam());
    }

    public static void execute(KeyCode kc, Game game) {
        var possibleStructure = StructureStore.getStructureFromKey(kc);

        possibleStructure.ifPresent(structure -> {
            if (haveEnoughForStructure(structure, game.getPlayer())) {
                var selectedGameBlock = game.getSelectedGameBlock();

                if (selectedGameBlock != null && selectedGameBlock.doesntHaveStructureOrUnitOnIt() && selectedGameBlock.isPlayerMovable() && legalStructurePlacement(structure, selectedGameBlock)) {
                    setBlockAndStructureInteraction(game, selectedGameBlock,
                            structure);
                    structure.setPos(selectedGameBlock.getPosition());
                    structure.setTeam(game.getTeam());
                    game.getPlayer().structureManager.addStructure(structure);
                }
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

    /**
     * @param structure Structure to test
     * @param gameBlock Block to possibly place structure on
     * @return Whether it is valid for a structure to be placed on a given tile.
     * Currently, it is mainly used for testing whether a resource miner is
     * on a tile with a resource on it and if so that that resource is
     * of the correct type.
     */
    private static boolean legalStructurePlacement(Structure structure,
                                                   GameBlock gameBlock) {

        if (structure instanceof ResourceStructure) {
            if (!gameBlock.hasResourceOnIt()) {
                return false;
            }

            return ((ResourceStructure) structure).resourceType.equals(gameBlock.getResource().getResourceType().toString());
        }

        return true;

    }
}
