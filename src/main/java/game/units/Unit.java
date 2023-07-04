package game.units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.common.Movable;
import game.common.Updatable;
import game.world.block.GameBlock;
import org.hexworks.zircon.api.data.Position;

@SuppressWarnings("unused")
public class Unit implements Updatable, Movable {
    @JsonIgnore
    int currentHealth;
    @JsonIgnore
    Position pos;

    @JsonIgnore
    GameBlock blockUnitIsOn;

    private char key;
    private String name;
    private int maxHealth;
    private int attack;
    private int defense;

    // Look into having this be a resource amount that is transformed from
    // these values that are gathered through Jackson
    private int goldCost;
    private int ironCost;
    private int woodCost;


    Unit(int currentHealth, Position pos, GameBlock blockUnitIsOn, char key,
         String name, int maxHealth, int attack, int defense, int goldCost,
         int ironCost, int woodCost) {
        this.currentHealth = currentHealth;
        this.pos = pos;
        this.blockUnitIsOn = blockUnitIsOn;
        this.key = key;
        this.name = name;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.goldCost = goldCost;
        this.ironCost = ironCost;
        this.woodCost = woodCost;
    }

    // Default constructor that is needed by Jackson JSON parser
    Unit() {
    }

    public char getKey() {
        return this.key;
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

    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        this.goldCost = goldCost;
    }

    public int getIronCost() {
        return ironCost;
    }

    public void setIronCost(int ironCost) {
        this.ironCost = ironCost;
    }

    public int getWoodCost() {
        return this.woodCost;
    }

    public void setWoodCost(int woodCost) {
        this.woodCost = woodCost;
    }

    public void changeHealth(int dx) {
        this.currentHealth += dx;
    }

    public GameBlock getBlockUnitIsOn() {
        return this.blockUnitIsOn;
    }

    public void setBlockUnitIsOn(GameBlock blockUnitIsOn) {
        this.blockUnitIsOn = blockUnitIsOn;
    }

    public Unit copy() {
        return new Unit(this.currentHealth, this.pos, this.blockUnitIsOn,
                this.key, this.name, this.maxHealth, this.attack,
                this.defense, this.goldCost, this.ironCost, this.woodCost);
    }

    @Override
    public void update() {
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void moveX(int x) {
    }

    @Override
    public void moveY(int y) {
    }

}
