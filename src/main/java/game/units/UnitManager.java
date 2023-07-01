package game.units;

import java.util.ArrayList;
import java.util.List;

public class UnitManager {
    List<Unit> units;

    public UnitManager() {
        this.units = new ArrayList<>();
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }
}
