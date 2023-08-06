package game.resources;

import org.hexworks.zircon.api.color.TileColor;

public class Iron extends Resource {
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

    @Override
    public TileColor getResourceColor() {
        return TileColor.fromString("#D1CEC4");
    }

}
