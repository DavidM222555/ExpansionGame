package game.units;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.Component;
import org.hexworks.zircon.api.component.VBox;
import org.hexworks.zircon.api.data.Position;
import org.hexworks.zircon.api.data.Size;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.internal.component.renderer.VerticalScrollBarRenderer;
import org.hexworks.zircon.internal.fragment.impl.VerticalScrollableList;

import java.io.IOException;
import java.util.Map;

import static org.hexworks.zircon.api.ComponentDecorations.box;

public class UnitStore {
    static Map<String, Unit> unitMap;

    private UnitStore(Map<String, Unit> unitMap) {
        UnitStore.unitMap = unitMap;
    }

    public static UnitStore fromJSONDirectory(String unitDirectory) throws IOException {
        unitMap = UnitReader.readUnits(unitDirectory);

        return new UnitStore(unitMap);
    }

    public Component toComponent(int width) {
        VBox unitComponent =
                Components.vbox().withPreferredSize(width, 7).withDecorations(box(BoxType.SINGLE, "Units")).build();

        Function2<String, Integer, kotlin.Unit> onItemCallback = (String item
                , Integer idx) -> {
            System.out.println("You clicked on: " + item);
            return kotlin.Unit.INSTANCE;
        };

        Function1<String, String> renderItemCallback = (String item) -> item;

        VerticalScrollableList<String> vsl =
                new VerticalScrollableList<>(Size.create(width - 2, 5),
                        Position.create(0, 0),
                        unitMap.keySet().stream().toList(), onItemCallback,
                        renderItemCallback, new VerticalScrollBarRenderer());

        unitComponent.addFragment(vsl);


        return unitComponent;
    }
}
