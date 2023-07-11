package game.structures;

import org.hexworks.zircon.api.data.Position;

public class DefenseStructure extends Structure {
    public int attack;
    public int attackRadius;

    DefenseStructure(char key, String name, int maxHealth, int visionRadius,
                     int controlRadius, int controlStrength, int controlRate,
                     Position pos, int health, int attack, int attackRadius) {
        this.key = key;
        this.name = name;
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.visionRadius = visionRadius;
        this.controlRadius = controlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
        this.attack = attack;
        this.attackRadius = attackRadius;
    }

    @Override
    DefenseStructure copy() {
        return new DefenseStructure(this.key, this.name, this.maxHealth,
                this.visionRadius, this.controlRadius, this.controlStrength,
                this.controlRate, this.pos, this.health, this.attack,
                this.attackRadius);
    }
}
