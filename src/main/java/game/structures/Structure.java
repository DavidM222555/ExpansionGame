package game.structures;

import game.common.Updatable;
import org.hexworks.zircon.api.data.Position;

public abstract class Structure implements Updatable {
    Position pos;
    int health;
    int maxHealth;
    char structChar;
    int visionRadius;
    int controlRadius;
    int controlStrength;


    Structure(Position pos, int health, int maxHealth, char structChar,
              int visionRadius, int controlRadius, int controlStrength) {
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.structChar = structChar;
        this.visionRadius = visionRadius;
        this.controlRadius = controlRadius;
        this.controlStrength = controlStrength;
    }

}
