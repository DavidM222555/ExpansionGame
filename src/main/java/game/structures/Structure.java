package game.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hexworks.zircon.api.data.Position;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@JsonSubTypes.Type(DefenseStructure.class),
        @JsonSubTypes.Type(ResourceStructure.class),
        @JsonSubTypes.Type(BasicStructure.class)})
public abstract class Structure {
    char key;
    String name;
    int maxHealth;
    int visionRadius;
    int controlRadius;
    int controlStrength;
    int controlRate;

    @JsonIgnore
    Position pos;
    @JsonIgnore
    int health;

    Structure() {
    }

    abstract Structure copy();

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getVisionRadius() {
        return visionRadius;
    }

    public void setVisionRadius(int visionRadius) {
        this.visionRadius = visionRadius;
    }

    public int getControlRadius() {
        return controlRadius;
    }

    public void setControlRadius(int controlRadius) {
        this.controlRadius = controlRadius;
    }

    public int getControlStrength() {
        return controlStrength;
    }

    public void setControlStrength(int controlStrength) {
        this.controlStrength = controlStrength;
    }

    public int getControlRate() {
        return controlRate;
    }

    public void setControlRate(int controlRate) {
        this.controlRate = controlRate;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
