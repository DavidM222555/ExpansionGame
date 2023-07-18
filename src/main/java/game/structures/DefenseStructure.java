package game.structures;

import org.hexworks.zircon.api.data.Position;

public class DefenseStructure extends Structure {
    public int attack;
    public int attackRadius;

    DefenseStructure() {
        super();
    }

    DefenseStructure(char key, String name, int maxHealth, int visionRadius,
                     int maxControlRadius, int controlStrength,
                     int controlRate, Position pos, int health, int attack,
                     int attackRadius, int goldCost, int ironCost,
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
        this.attack = attack;
        this.attackRadius = attackRadius;
        this.goldCost = goldCost;
        this.ironCost = ironCost;
        this.woodCost = woodCost;
    }

    @Override
    DefenseStructure copy() {
        return new DefenseStructure(this.key, this.name, this.maxHealth,
                this.visionRadius, this.maxControlRadius,
                this.controlStrength, this.controlRate, this.pos, this.health
                , this.attack, this.attackRadius, this.goldCost,
                this.ironCost, this.woodCost);
    }
}
