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

                if (selectedGameBlock != null && !selectedGameBlock.hasStructureOrUnitOnIt() && selectedGameBlock.isPlayerMovable()) {
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
        System.out.println("Unit iron cost: " + structure.getIronCost() + " " + "wood cost: " + structure.getWoodCost());

        return (structure.getGoldCost() <= player.getGoldAmount() && structure.getIronCost() <= player.getIronAmount() && structure.getWoodCost() <= player.getWoodAmount());
    }

    private static void handleStructureCost(Structure structure,
                                            Player player) {
        player.resourceManager.changeResourceAmounts(-1 * structure.getGoldCost(), -1 * structure.getIronCost(), -1 * structure.getWoodCost());
    }
}
