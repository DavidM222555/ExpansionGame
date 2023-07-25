package game.resources;

public class Wood extends Resource {
    static char resourceChar = '*';

    Wood(int startingAmount) {
        super(startingAmount);
    }

    @Override
    public char getResourceChar() {
        return '*';
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.WOOD;
    }
}
