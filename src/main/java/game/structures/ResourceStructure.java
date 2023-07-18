package game.structures;

import org.hexworks.zircon.api.data.Position;

public class ResourceStructure extends Structure {
    public String resourceType;

    ResourceStructure() {
        super();
    }

    ResourceStructure(char key, String name, int maxHealth, int visionRadius,
                      int maxControlRadius, int controlStrength,
                      int controlRate, Position pos, int health,
                      String resourceType, int goldCost, int ironCost,
                      int woodCost) {
        this.key = key;
        this.name = name;
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.visionRadius = visionRadius;
        this.maxControlRadius = maxControlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
        this.resourceType = resourceType;
        this.goldCost = goldCost;
        this.ironCost = ironCost;
        this.woodCost = woodCost;
    }

    @Override
    ResourceStructure copy() {
        return new ResourceStructure(this.key, this.name, this.maxHealth,
                this.visionRadius, this.maxControlRadius,
                this.controlStrength, this.controlRate, this.pos, this.health
                , this.resourceType, this.goldCost, this.ironCost,
                this.woodCost);
    }
}
