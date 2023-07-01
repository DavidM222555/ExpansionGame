package game.input;

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
            this.game.world.scrollForwardBy(1);
        } else if (keyCode.equals(KeyCode.KEY_D)) {
            this.game.world.scrollRightBy(1);
        } else if (keyCode.equals(KeyCode.KEY_W)) {
            this.game.world.scrollBackwardBy(1);
        } else if (keyCode.equals(KeyCode.KEY_A)) {
            this.game.world.scrollLeftBy(1);
        }
    }

    public void executeMouseEvent(MouseEvent mouseEvent) {
        var position = mouseEvent.getPosition();
        var offset = this.game.world.getVisibleOffset().to2DPosition();

        var selectedBlock = this.game.world.getBlock(position.plus(offset));

        SelectGameBlockCommand.execute(this.game, selectedBlock);
    }

}
