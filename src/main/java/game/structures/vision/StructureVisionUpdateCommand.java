package game.structures.vision;

import game.Game;
import game.structures.Structure;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.data.Position;

public class StructureVisionUpdateCommand {
    public static void execute(Game game, Structure structure) {
        var currentRadius = structure.getCurrentVisionRadius() + 1;
        System.out.println("Current vision radius: " + currentRadius);
        System.out.println("Max vision radius: " + structure.getMaxVisionRadius());

        if (currentRadius >= structure.getMaxVisionRadius()) {
            return;
        }

        var structurePos = structure.getPos();

        var minX = Math.max((structurePos.getX() - currentRadius), 0);
        var maxX = Math.min((structurePos.getX() + currentRadius),
                game.getGameBoardWidth() - 1);

        var minY = Math.max((structurePos.getY() - currentRadius), 0);
        var maxY = Math.min((structurePos.getY() + currentRadius),
                game.getGameBoardHeight() - 1);


        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                GameBlock gameBlockAtPos =
                        game.getWorld().getBlock(Position.create(i, j));

                if (gameBlockAtPos != null) {
                    gameBlockAtPos.setVisible(true);
                    gameBlockAtPos.refreshTileContent();
                }
            }
        }

        System.out.println("Down here ");
        structure.setCurrentVisionRadius(currentRadius);
    }
}
