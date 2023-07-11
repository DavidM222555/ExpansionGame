package game.structures;

import game.Game;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.uievent.KeyCode;

public class BuyStructureCommand {
    private static void setBlockAndStructureInteraction(GameBlock selectedGameBlock, Structure structure) {
        selectedGameBlock.setStructure(structure);
    }

    public static void execute(KeyCode kc, Game game) {
        var possibleStructure = StructureStore.getStructureFromKey(kc);

        possibleStructure.ifPresent(structure -> {
            var selectedGameBlock = game.getSelectedGameBlock();

            if (selectedGameBlock != null && !selectedGameBlock.hasStructureOrUnitOnIt() && selectedGameBlock.isPlayerMovable()) {
                setBlockAndStructureInteraction(selectedGameBlock, structure);
            }
        });
    }
}
