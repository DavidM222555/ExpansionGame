package game.gui;

import org.hexworks.zircon.api.Functions;

import java.io.IOException;

public class ViewManager {
    private final GameView gameView;

    public ViewManager() throws IOException, InterruptedException {
        // Initialize the views and then dock the start menu
        StartMenuView startMenuView = new StartMenuView();

        startMenuView.dock();
        gameView = new GameView();

        // Set method for changing the view to the game screen on activation
        startMenuView.startGameButton.onActivated(Functions.fromConsumer(event -> {
            System.out.println("This guy is activated");
            startMenuView.replaceWith(gameView);
        }));

        // Set method for exiting game with 0 status on exit game button
        // activation.
        startMenuView.exitGameButton.onActivated(Functions.fromConsumer(event -> System.exit(0)));
    }

}
