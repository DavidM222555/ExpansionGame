package game.structures;

import org.hexworks.zircon.api.data.Position;

public class ResourceStructure extends Structure {
    public String resourceType;
    public int resourceMiningRate;

    ResourceStructure() {
        super();
    }

    ResourceStructure(char key, String name, int maxHealth,
                      int maxVisionRadius, int maxControlRadius,
                      int controlStrength, int controlRate, Position pos,
                      int health, String resourceType, int resourceMiningRate
            , int goldCost, int ironCost, int woodCost) {
        this.key = key;
        this.name = name;
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.maxVisionRadius = maxVisionRadius;
        this.maxControlRadius = maxControlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
        this.resourceType = resourceType;
        this.resourceMiningRate = resourceMiningRate;
        this.goldCost = goldCost;
        this.ironCost = ironCost;
        this.woodCost = woodCost;
    }

    @Override
    ResourceStructure copy() {
        return new ResourceStructure(this.key, this.name, this.maxHealth,
                this.maxVisionRadius, this.maxControlRadius,
                this.controlStrength, this.controlRate, this.pos, this.health
                , this.resourceType, this.resourceMiningRate, this.goldCost,
                this.ironCost, this.woodCost);
    }
}
