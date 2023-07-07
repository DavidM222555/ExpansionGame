package game.structures;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StructureReader {
    static ObjectMapper objMapper = new ObjectMapper();

    public static Structure readStructure(String path) throws IOException {
        return objMapper.readValue(new File(path), Structure.class);
    }

    @NotNull
    public static Map<Character, Structure> readStructures(String structDirPath) throws IOException {
        Map<Character, Structure> structureMap = new HashMap<>();
        File fileFromPath = new File(structDirPath);

        if (!fileFromPath.exists()) {
            throw new IOException("Struct directory doesn't exist");
        }

        var dirFiles = fileFromPath.listFiles();

        if (dirFiles != null) {
            for (File child : dirFiles) {
                var structure = readStructure(child.getPath());

                structureMap.put(structure.key, structure);
            }
        }

        return structureMap;
    }
}
