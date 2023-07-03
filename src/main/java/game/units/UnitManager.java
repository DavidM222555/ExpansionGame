package game.units;

import java.util.ArrayList;
import java.util.List;

public class UnitManager {
    private final List<Unit> units;

    public UnitManager() {
        this.units = new ArrayList<>();
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }

    public List<Unit> getUnits() {
        return this.units;
    }

    public void printUnits() {
        System.out.println("Printing units ");
        for (var unit : units) {
            System.out.println("Unit name: " + unit.getName() + " is at " +
                    "position: " + unit.getPos());
        }
    }
}
