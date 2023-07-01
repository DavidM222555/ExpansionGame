package game.resources;

public class ResourceCost {
    Gold goldAmount;
    Iron ironAmount;
    Wood woodAmount;

    ResourceCost(int goldAmount, int ironAmount, int woodAmount) {
        this.goldAmount = new Gold(goldAmount);
        this.ironAmount = new Iron(ironAmount);
        this.woodAmount = new Wood(woodAmount);
    }

}
