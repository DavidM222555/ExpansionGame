package game.resources;

public class ResourceAmount {
    Gold goldAmount;
    Iron ironAmount;
    Wood woodAmount;

    ResourceAmount(int goldAmount, int ironAmount, int woodAmount) {
        this.goldAmount = new Gold(goldAmount);
        this.ironAmount = new Iron(ironAmount);
        this.woodAmount = new Wood(woodAmount);
    }

    @Override
    public String toString() {
        return "G:" + this.goldAmount.amount + "I:" + this.ironAmount.amount + "W:" + this.woodAmount.amount;
    }
}
