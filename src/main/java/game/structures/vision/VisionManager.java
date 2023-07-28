package game.structures.vision;

import game.world.block.GameBlock;

import java.util.HashSet;
import java.util.Set;

public class VisionManager {
    private final Set<GameBlock> visibleBlocks;

    VisionManager() {
        this.visibleBlocks = new HashSet<>();
    }

    public void addTile(GameBlock gb) {
        visibleBlocks.add(gb);
    }

    public void removeTile(GameBlock gb) {
        visibleBlocks.remove(gb);
    }

    public boolean isVisible(GameBlock gb) {
        return this.visibleBlocks.contains(gb);
    }
}
