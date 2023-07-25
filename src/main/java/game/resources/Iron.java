package game.resources;

public class Iron extends Resource {
    static char resourceChar = '^';

    Iron(int startingAmount) {
        super(startingAmount);
    }

    @Override
    public char getResourceChar() {
        return '^';
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.IRON;
    }

}
