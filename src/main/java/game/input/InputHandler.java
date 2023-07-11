package game.input;

import game.BuyCommand;
import game.Game;
import game.world.block.SelectGameBlockCommand;
import org.hexworks.zircon.api.uievent.KeyCode;
import org.hexworks.zircon.api.uievent.MouseEvent;


public class InputHandler {
    Game game;

    public InputHandler(Game game) {
        this.game = game;
    }

    public void executeKeyboardEvent(KeyCode keyCode) {
        if (keyCode.equals(KeyCode.KEY_S)) {
            this.game.getWorld().scrollForwardBy(1);
        } else if (keyCode.equals(KeyCode.KEY_D)) {
            this.game.getWorld().scrollRightBy(1);
        } else if (keyCode.equals(KeyCode.KEY_W)) {
            this.game.getWorld().scrollBackwardBy(1);
        } else if (keyCode.equals(KeyCode.KEY_A)) {
            this.game.getWorld().scrollLeftBy(1);
        } else {
            BuyCommand.execute(keyCode, game);
        }
    }

    public void executeMouseEvent(MouseEvent mouseEvent) {
        var position = mouseEvent.getPosition();
        var offset = this.game.getWorld().getVisibleOffset().to2DPosition();

        var selectedBlock =
                this.game.getWorld().getBlock(position.plus(offset));

        SelectGameBlockCommand.execute(this.game, selectedBlock);
    }

}
