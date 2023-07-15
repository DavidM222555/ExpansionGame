package game.structures;

import org.hexworks.zircon.api.data.Position;

public class BasicStructure extends Structure {
    BasicStructure() {
        super();
    }

    BasicStructure(char key, String name, int maxHealth, int visionRadius,
                   int controlRadius, int controlStrength, int controlRate,
                   Position pos, int health, int goldCost, int ironCost,
                   int woodCost) {
        this.key = key;
        this.name = name;
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.visionRadius = visionRadius;
        this.controlRadius = controlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
        this.goldCost = goldCost;
        this.ironCost = ironCost;
        this.woodCost = woodCost;
    }

    BasicStructure copy() {
        return new BasicStructure(this.key, this.name, this.maxHealth,
                this.visionRadius, this.controlRadius, this.controlStrength,
                this.controlRate, this.pos, this.health, this.goldCost,
                this.ironCost, this.woodCost);
    }
}
