package game.units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.common.Movable;
import game.common.Updatable;
import org.hexworks.zircon.api.data.Position;

public class Unit implements Updatable, Movable {
    String name;
    int health;
    int attack;
    int defense;
    char unitChar;

    @JsonIgnore
    Position pos;


    Unit(String name, char unitChar, int health, int attack, int defense,
         Position pos) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.unitChar = unitChar;
        this.pos = pos;
    }

    Unit() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public char getUnitChar() {
        return unitChar;
    }

    public void setUnitChar(char unitChar) {
        this.unitChar = unitChar;
    }


    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
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
