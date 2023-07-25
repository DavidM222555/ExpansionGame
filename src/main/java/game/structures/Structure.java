package game.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import game.Game;
import game.structures.control.StructureControlUpdateCommand;
import game.teams.Team;
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
    int maxControlRadius;
    int controlStrength;
    int controlRate;
    int goldCost;
    int ironCost;
    int woodCost;

    @JsonIgnore
    Position pos;
    @JsonIgnore
    int health;
    @JsonIgnore
    int currentControlRadius;
    @JsonIgnore
    Team team;
    

    Structure() {
        this.currentControlRadius = 0;
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

    public int getGoldCost() {
        return this.goldCost;
    }

    public void setGoldCost(int newGoldCost) {
        this.goldCost = newGoldCost;
    }

    public int getIronCost() {
        return this.ironCost;
    }

    public void setIronCost(int newIronCost) {
        this.ironCost = newIronCost;
    }

    public int getWoodCost() {
        return this.woodCost;
    }

    public void setWoodCost(int newWoodCost) {
        this.woodCost = newWoodCost;
    }

    public int getCurrentControlRadius() {
        return this.currentControlRadius;
    }

    public void setCurrentControlRadius(int newCurrentControlRadius) {
        this.currentControlRadius = newCurrentControlRadius;
    }

    public int getMaxControlRadius() {
        return this.maxControlRadius;
    }

    public Position getPos() {
        return this.pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team newTeam) {
        this.team = newTeam;
    }

    public void updateControl(Game game) {
        StructureControlUpdateCommand.execute(game, this, this.team);
    }
}
