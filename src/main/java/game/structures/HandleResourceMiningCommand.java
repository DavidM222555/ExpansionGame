package game.structures;

import game.player.Player;
import game.resources.Resource;
import game.world.block.GameBlock;

public class HandleResourceMiningCommand {

    /**
     * @param player    Player that controls the structure that is mining a
     *                  resource
     * @param gameBlock Block that contains the resource and the structure
     */
    public static void execute(Player player, GameBlock gameBlock) {
        var resourceOnTile = gameBlock.getResource();
        var structureOnTile = gameBlock.getStructure();

        if (resourceOnTile != null && structureOnTile instanceof ResourceStructure) {
            var currentResourceAmount = resourceOnTile.getCurrentAmount();
            var resourceMiningRate =
                    ((ResourceStructure) structureOnTile).resourceMiningRate;

            System.out.println("Current resource amount: " + currentResourceAmount);

            if (currentResourceAmount >= 0) {
                if ((currentResourceAmount - resourceMiningRate) > 0) {
                    updateResourceAmounts(player, resourceOnTile,
                            structureOnTile, resourceMiningRate);
                }
                // Handle the case where the mining rate is greater than
                // the current amount of resources. If that is the case just
                // add all the resources that currently exist
                else if ((currentResourceAmount - resourceMiningRate) < 0) {
                    updateResourceAmounts(player, resourceOnTile,
                            structureOnTile, currentResourceAmount);
                }
            }
        }
    }

    private static void updateResourceAmounts(Player player,
                                              Resource resource,
                                              Structure structure,
                                              int amountToChange) {
        resource.changeCurrentAmount(-1 * amountToChange);

        var resourceType = resource.getResourceType();

        switch (resourceType) {
            case GOLD:
                player.updateResources(amountToChange, 0, 0);
                break;
            case IRON:
                player.updateResources(0, amountToChange, 0);
                break;
            case WOOD:
                player.updateResources(0, 0, amountToChange);
                break;
        }

    }

}
