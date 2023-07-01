package game.gui;

import org.hexworks.zircon.api.Functions;

import java.io.IOException;

public class ViewManager {
    private final GameView gameView;

    public ViewManager() throws IOException {
        // Initialize the views and then dock the start menu
        StartMenuView startMenuView = new StartMenuView();
        gameView = new GameView();

        startMenuView.dock();

        // Set method for changing the view to the game screen on start
        // game button activation
        startMenuView.startGameButton.onActivated(Functions.fromConsumer(event -> gameView.dock()));

        // Set method for exiting game with 0 status on exit game button
        // activation.
        startMenuView.exitGameButton.onActivated(Functions.fromConsumer(event -> System.exit(0)));
    }

}
