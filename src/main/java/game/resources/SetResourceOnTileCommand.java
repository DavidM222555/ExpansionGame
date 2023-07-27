package game.resources;

import game.world.block.GameBlock;

public class SetResourceOnTileCommand {
    public static void execute(GameBlock gameBlock, Resource resource) {
        if (!gameBlock.hasStructureOrUnitOnIt() && gameBlock.isPlayerMovable())
            gameBlock.setResource(resource);
    }
}
