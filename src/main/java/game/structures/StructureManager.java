package game.structures;

import java.util.ArrayList;
import java.util.List;

public class StructureManager {
    List<Structure> structures;

    public StructureManager() {
        this.structures = new ArrayList<>();
    }

    public void addStructure(Structure structure) {
        this.structures.add(structure);
    }

    public void removeStructure(Structure structure) {
        this.structures.remove(structure);
    }
}
