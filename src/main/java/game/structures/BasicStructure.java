package game.structures;

import org.hexworks.zircon.api.data.Position;

public class BasicStructure extends Structure {
    BasicStructure() {
        super();
    }

    BasicStructure(char key, int maxHealth, int visionRadius,
                   int controlRadius, int controlStrength, int controlRate,
                   Position pos, int health) {
        this.key = key;
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.visionRadius = visionRadius;
        this.controlRadius = controlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
    }

    BasicStructure copy() {
        return new BasicStructure(this.key, this.maxHealth, this.visionRadius
                , this.controlRadius, this.controlStrength, this.controlRate,
                this.pos, this.health);
    }
}
