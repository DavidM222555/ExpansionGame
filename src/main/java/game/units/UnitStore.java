package game.units;

import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.Component;

import java.io.IOException;
import java.util.Map;

public class UnitStore {
    Map<String, Unit> unitMap;

    private UnitStore(Map<String, Unit> unitMap) {
        this.unitMap = unitMap;
    }

    UnitStore fromJSONDirectory(String unitDirectory) throws IOException {
        unitMap = UnitReader.readUnits(unitDirectory);

        return new UnitStore(unitMap);
    }

    Component toComponent(int width) {
        Component vertScrollBar = Components.verticalScrollbar().build();

        return vertScrollBar;
    }
}
