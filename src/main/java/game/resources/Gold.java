package game.resources;

import org.hexworks.zircon.api.color.TileColor;

public class Gold extends Resource {
    Gold(int startingAmount) {
        super(startingAmount);
    }

    @Override
    public char getResourceChar() {
        return '&';
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.GOLD;
    }

    public TileColor getResourceColor() {
        return TileColor.fromString("#F5E071");
    }

}
