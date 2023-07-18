package game.structures.control;

import game.Game;
import game.structures.Structure;
import game.teams.Team;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.data.Position;

public class StructureControlUpdateCommand {
    public static void execute(Game game, Structure structure, Team team) {
        // Handles the major updates on a game update where we have to go
        // and expand the radius of each structure
        var currentRadius = structure.getCurrentControlRadius() + 1;

        if (currentRadius >= structure.getMaxControlRadius()) {
            return;
        }

        var structurePos = structure.getPos();

        var minX = Math.max((structurePos.getX() - currentRadius), 0);
        var maxX = Math.min((structurePos.getX() + currentRadius),
                game.getGameBoardWidth());

        var minY = Math.max((structurePos.getY() - currentRadius), 0);
        var maxY = Math.min((structurePos.getY() + currentRadius),
                game.getGameBoardHeight());


        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                GameBlock gameBlockAtPos =
                        game.getWorld().getBlock(Position.create(i, j));
                gameBlockAtPos.setTeam(team);
            }
        }

        structure.setCurrentControlRadius(currentRadius);
    }
}
