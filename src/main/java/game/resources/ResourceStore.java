package game.resources;

public class ResourceStore {
    public static Resource getResource(ResourceType rt, int amount) {
        return switch (rt) {
            case GOLD -> new Gold(amount);
            case IRON -> new Iron(amount);
            case WOOD -> new Wood(amount);
        };
    }
}
