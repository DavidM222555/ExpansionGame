package game.gui;

import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.Button;
import org.hexworks.zircon.api.component.ComponentAlignment;
import org.hexworks.zircon.api.component.Header;
import org.hexworks.zircon.api.component.VBox;
import org.hexworks.zircon.api.view.base.BaseView;

import static game.GAME_CONSTANTS.COLOR_THEME;
import static game.GAME_CONSTANTS.TILE_GRID;
import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.ComponentDecorations.shadow;
import static org.hexworks.zircon.api.Components.vbox;

public class StartMenuView extends BaseView {
    final String title = "EXPANSION";
    final Header header =
            Components.header().withText(title).withAlignmentWithin(TILE_GRID
                    , ComponentAlignment.TOP_CENTER).withDecorations(box(),
                    shadow()).build();
    VBox menu = vbox().withPreferredSize(this.getScreen().getWidth() / 4,
            this.getScreen().getHeight() / 4).withAlignmentWithin(TILE_GRID,
            ComponentAlignment.CENTER).withSpacing(1).withDecorations(box(),
            shadow()).build();
    public final Button startGameButton = Components.button().withText("START"
            + " GAME").withAlignmentWithin(menu,
            ComponentAlignment.TOP_CENTER).build();

    public final Button viewRunsButton =
            Components.button().withText("VIEW " + "RUNS").withAlignmentWithin(menu, ComponentAlignment.TOP_CENTER).build();

    public final Button exitGameButton =
            Components.button().withText("EXIT").withAlignmentWithin(menu,
                    ComponentAlignment.TOP_CENTER).build();

    public StartMenuView() {
        super(TILE_GRID, COLOR_THEME);

        menu.addComponent(startGameButton);
        menu.addComponent(viewRunsButton);
        menu.addComponent(exitGameButton);

        this.getScreen().addComponent(header);
        this.getScreen().addComponent(menu);
    }
}
