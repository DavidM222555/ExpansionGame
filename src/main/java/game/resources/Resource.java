package game.resources;

public abstract class Resource {
    int amount;
    String name;

    Resource(int startingAmount) {
        this.amount = startingAmount;
    }

    public void changeCurrentAmount(int dx) {
        this.amount += dx;
    }

    public int getCurrentAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }
}
