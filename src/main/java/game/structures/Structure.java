package game.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hexworks.zircon.api.data.Position;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@JsonSubTypes.Type(DefenseStructure.class),
        @JsonSubTypes.Type(ResourceStructure.class),})
public abstract class Structure {
    char key;
    int maxHealth;
    int visionRadius;
    int controlRadius;
    float controlStrength;
    float controlRate;

    @JsonIgnore
    Position pos;
    @JsonIgnore
    int health;

    Structure() {
    }

    Structure(Position pos, int health, int maxHealth, int visionRadius,
              int controlRadius, int controlStrength, float controlRate) {
        this.pos = pos;
        this.health = health;
        this.maxHealth = maxHealth;
        this.visionRadius = visionRadius;
        this.controlRadius = controlRadius;
        this.controlStrength = controlStrength;
        this.controlRate = controlRate;
    }
}
