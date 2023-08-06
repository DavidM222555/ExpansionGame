package game.resources;

import org.hexworks.zircon.api.color.TileColor;

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

    @Override
    public TileColor getResourceColor() {
        return TileColor.fromString("#9A695A");
    }
}
