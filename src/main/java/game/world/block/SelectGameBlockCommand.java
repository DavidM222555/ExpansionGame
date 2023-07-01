package game.world.block;

import game.Game;
import org.hexworks.zircon.api.Modifiers;

public class SelectGameBlockCommand {
    public static void execute(Game game, GameBlock gameBlockToSelect) {
        if (game.selectedGameBlock != null) {
            game.selectedGameBlock.setContent(game.selectedGameBlock.getContent().withRemovedModifiers(Modifiers.blink(), Modifiers.border()));
        }

        gameBlockToSelect.setContent(gameBlockToSelect.getContent().withModifiers(Modifiers.blink(), Modifiers.border()));
        game.setSelectedGameBlock(gameBlockToSelect);
    }
}
