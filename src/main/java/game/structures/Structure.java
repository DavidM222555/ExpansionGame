package game.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hexworks.zircon.api.data.Position;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@JsonSubTypes.Type(value = DefenseStructure.class),
        @JsonSubTypes.Type(ResourceStructure.class),
        @JsonSubTypes.Type(BasicStructure.class)})
public abstract class Structure {
    char key;
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

    Structure(char key, int maxHealth, int visionRadius, int controlRadius,
              int controlStrength, int controlRate, Position pos, int health) {
        this.key = key;
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.visionRadius = visionRadius;
        this.controlRadius = controlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
    }

    abstract Structure copy();

}
