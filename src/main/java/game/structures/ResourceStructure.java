package game.structures;

import org.hexworks.zircon.api.data.Position;

public class ResourceStructure extends Structure {
    public String resourceType;

    ResourceStructure() {
        super();
    }

    ResourceStructure(char key, int maxHealth, int visionRadius,
                      int controlRadius, int controlStrength, int controlRate
            , Position pos, int health, String resourceType) {
        this.key = key;
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.visionRadius = visionRadius;
        this.controlRadius = controlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
        this.resourceType = resourceType;
    }

    @Override
    ResourceStructure copy() {
        return new ResourceStructure(this.key, this.maxHealth,
                this.visionRadius, this.controlRadius, this.controlStrength,
                this.controlRate, this.pos, this.health, this.resourceType);
    }
}
