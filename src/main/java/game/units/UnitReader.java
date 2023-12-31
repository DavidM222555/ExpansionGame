package game.units;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UnitReader {
    static ObjectMapper objMapper = new ObjectMapper();

    public static Unit readUnit(String path) throws IOException {
        return objMapper.readValue(new File(path), Unit.class);
    }

    @NotNull
    public static Map<Character, Unit> readUnits(String unitDirPath) throws IOException {
        Map<Character, Unit> unitMap = new HashMap<>();
        File fileFromPath = new File(unitDirPath);

        if (!fileFromPath.exists()) {
            throw new IOException("Unit directory doesn't exist at path: " + unitDirPath);
        }

        var dirFiles = fileFromPath.listFiles();

        if (dirFiles != null) {
            for (File child : dirFiles) {
                var unit = readUnit(child.getPath());

                unitMap.put(unit.getKey(), unit);
            }
        }

        return unitMap;
    }
}
