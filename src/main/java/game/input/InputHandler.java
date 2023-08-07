package game.input;

import game.BuyCommand;
import game.gui.GameView;
import game.world.block.SelectGameBlockCommand;
import org.hexworks.zircon.api.uievent.KeyCode;
import org.hexworks.zircon.api.uievent.MouseEvent;


public class InputHandler {
    GameView gameView;

    public InputHandler(GameView game) {
        this.gameView = game;
    }

    public void executeKeyboardEvent(KeyCode keyCode) {
        if (keyCode.equals(KeyCode.KEY_S)) {
            this.gameView.getWorld().scrollForwardBy(1);
        } else if (keyCode.equals(KeyCode.KEY_D)) {
            this.gameView.getWorld().scrollRightBy(1);
        } else if (keyCode.equals(KeyCode.KEY_W)) {
            this.gameView.getWorld().scrollBackwardBy(1);
        } else if (keyCode.equals(KeyCode.KEY_A)) {
            this.gameView.getWorld().scrollLeftBy(1);
        } else if (keyCode.equals(KeyCode.SPACE)) {
            this.gameView.takeTurn();
        } else {
            BuyCommand.execute(keyCode, gameView.getGame(),
                    gameView.getGame().getPlayer());
        }
    }

    public void executeMouseEvent(MouseEvent mouseEvent) {
        var position = mouseEvent.getPosition();
        var offset = this.gameView.getWorld().getVisibleOffset().to2DPosition();

        var selectedBlock =
                this.gameView.getWorld().getBlock(position.plus(offset));

        SelectGameBlockCommand.execute(this.gameView.getGame(), selectedBlock);
    }

}
