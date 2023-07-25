package game.resources;

public abstract class Resource {
    int amount;

    Resource(int amount) {
        this.amount = amount;
    }

    public void changeCurrentAmount(int dx) {
        this.amount += dx;
    }

    public int getCurrentAmount() {
        return this.amount;
    }

    public abstract char getResourceChar();

    public abstract ResourceType getResourceType();

}
