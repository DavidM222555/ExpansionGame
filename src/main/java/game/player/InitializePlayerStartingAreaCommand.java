package game.player;

import game.Game;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.data.Position;

public class InitializePlayerStartingAreaCommand {
    public static void execute(Game game, Player player, Position pos) {
        int radius = 15;

        for (int i = pos.getX() - radius; i < pos.getX() + radius; i++) {
            for (int j = pos.getY() - radius; j < pos.getY(); j++) {
                var blockAtPos = game.getBlockAtPosOrNull(Position.create(i,
                        j));

                blockAtPos.ifPresent((block) -> {
                    InitializePlayerStartingAreaCommand.handleSettingBlockToTeam(block, player);
                });
            }
        }
    }

    private static void handleSettingBlockToTeam(GameBlock block,
                                                 Player player) {
        if (block.isPlayerMovable()) {
            block.setTeam(player.getTeam());
        }
    }
}
