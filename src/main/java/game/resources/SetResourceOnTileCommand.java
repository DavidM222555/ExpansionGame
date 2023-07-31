package game.resources;

import game.world.block.GameBlock;

public class SetResourceOnTileCommand {
    public static void execute(GameBlock gameBlock, Resource resource) {
        if (gameBlock.doesntHaveStructureOrUnitOnIt() && gameBlock.isPlayerMovable())
            gameBlock.setResource(resource);
    }
}
