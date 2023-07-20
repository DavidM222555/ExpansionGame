package game.resources;

public class Gold extends Resource {
    Gold(int startingAmount) {
        super(startingAmount);
    }

    @Override
    public char getResourceChar() {
        return '&';
    }
}
