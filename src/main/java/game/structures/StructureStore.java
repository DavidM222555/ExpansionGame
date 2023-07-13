package game.structures;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.Component;
import org.hexworks.zircon.api.component.VBox;
import org.hexworks.zircon.api.data.Position;
import org.hexworks.zircon.api.data.Size;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.uievent.KeyCode;
import org.hexworks.zircon.internal.component.renderer.VerticalScrollBarRenderer;
import org.hexworks.zircon.internal.fragment.impl.VerticalScrollableList;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.hexworks.zircon.api.ComponentDecorations.box;

public class StructureStore {
    private static Map<Character, Structure> structureMap;

    private StructureStore(Map<Character, Structure> structureMap) {
        StructureStore.structureMap = structureMap;
    }

    public static StructureStore fromJSONDirectory(String structureDir) throws IOException {
        structureMap = StructureReader.readStructures(structureDir);

        return new StructureStore(structureMap);
    }

    public static boolean containsKey(Character key) {
        return structureMap.containsKey(key);
    }

    public static Optional<Structure> getStructureFromKey(KeyCode kc) {
        if (kc != null) {
            Character ch = kc.toCharOrNull();
            return Optional.ofNullable(structureMap.get(ch).copy());
        }

        return Optional.empty();
    }

    public Component toComponent(int width) {
        VBox unitComponent =
                Components.vbox().withPreferredSize(width, 7).withDecorations(box(BoxType.SINGLE, "Structures")).build();

        Function2<String, Integer, Unit> onItemCallback = (String item,
                                                           Integer idx) -> {
            System.out.println("You clicked on: " + item);
            return kotlin.Unit.INSTANCE;
        };

        Function1<String, String> renderItemCallback = (String item) -> item;

        VerticalScrollableList<String> vsl =
                new VerticalScrollableList<>(Size.create(width - 2, 5),
                        Position.create(0, 0),
                        structureMap.values().stream().map(game.structures.Structure::getName).toList(), onItemCallback, renderItemCallback, new VerticalScrollBarRenderer());

        unitComponent.addFragment(vsl);

        return unitComponent;
    }
}
