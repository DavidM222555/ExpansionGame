package game.gui;

public class SendTextToLogCommand {
    public static void execute(String text) {
        GameView.LOG_AREA.addInlineText(text);
        GameView.LOG_AREA.commitInlineElements();
    }
}
