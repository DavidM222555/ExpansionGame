package game.structures;

import org.hexworks.zircon.api.uievent.KeyCode;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class StructureStore {
    private static Map<Character, Structure> structureMap;

    private StructureStore(Map<Character, Structure> structureMap) {
        StructureStore.structureMap = structureMap;
    }

    public static StructureStore fromJSONDirectory(String structureDir) throws IOException {
        structureMap = StructureReader.readStructures(structureDir);

        return new StructureStore(structureMap);
    }

    public static Optional<Structure> getStructureFromKey(KeyCode kc) {
        if (kc != null) {
            Character ch = kc.toCharOrNull();
            return Optional.ofNullable(structureMap.get(ch).copy());
        }

        return Optional.empty();
    }
}
