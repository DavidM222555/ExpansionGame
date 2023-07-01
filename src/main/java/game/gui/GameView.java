package game.gui;

import game.GAME_CONSTANTS;
import game.Game;
import game.world.block.TileStore;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.GameComponents;
import org.hexworks.zircon.api.component.*;
import org.hexworks.zircon.api.component.renderer.ComponentRenderer;
import org.hexworks.zircon.api.game.ProjectionMode;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.uievent.KeyboardEventType;
import org.hexworks.zircon.api.uievent.MouseEventType;
import org.hexworks.zircon.api.uievent.UIEventResponse;
import org.hexworks.zircon.api.view.base.BaseView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static game.GAME_CONSTANTS.*;
import static org.hexworks.zircon.api.ComponentDecorations.box;

public class GameView extends BaseView {
    Game game = new Game();

    VBox mainContainer =
            Components.vbox().withPreferredSize(this.getScreen().getSize()).build();
    HBox gameAndSidePanelContainer =
            Components.hbox().withPreferredSize(this.getScreen().getSize()).build();

    VBox sidePanel =
            Components.vbox().withPreferredSize(GAME_CONSTANTS.SIDEBAR_WIDTH,
                    GAME_CONSTANTS.SIDEBAR_HEIGHT).withDecorations(box()).build();

    VBox gameAndLogArea =
            Components.vbox().withPreferredSize(GAME_CONSTANTS.MAIN_AREA_WIDTH, GAME_CONSTANTS.MAIN_AREA_HEIGHT).build();

    LogArea logArea =
            Components.logArea().withPreferredSize(GAME_CONSTANTS.LOGAREA_WIDTH, GAME_CONSTANTS.LOG_AREA_HEIGHT).withDecorations(box(BoxType.DOUBLE, "LOG")).build();


    public GameView() throws IOException {
        super(TILE_GRID, COLOR_THEME);

        var sidePanelContentWidth = this.sidePanel.getContentSize().getWidth();

        var resourceComponent =
                this.game.player.resourceManager.toComponent(sidePanelContentWidth);


        this.sidePanel.addComponent(resourceComponent);
        this.setupUnitAndStructureGroup(this.sidePanel);

        var unitListComponent =
                this.game.unitStore.toComponent(sidePanelContentWidth);
        this.sidePanel.addComponent(unitListComponent);

        ComponentRenderer<Panel> gameRenderer =
                GameComponents.newGameAreaComponentRenderer(game.world,
                        ProjectionMode.TOP_DOWN, TileStore.GROUND_TILE);


        var gamePanel = Components.panel().withPreferredSize(GAME_AREA_WIDTH,
                GAME_AREA_HEIGHT).withComponentRenderer(gameRenderer).build();

        gameAndLogArea.addComponent(gamePanel);
        gameAndLogArea.addComponent(logArea);

        gameAndSidePanelContainer.addComponent(gameAndLogArea);
        gameAndSidePanelContainer.addComponent(sidePanel);

        mainContainer.addComponent(gameAndSidePanelContainer);

        this.getScreen().addComponent(mainContainer);

        this.setupInputCallbacks();
    }

    private void setupInputCallbacks() {
        this.getScreen().handleKeyboardEvents(KeyboardEventType.KEY_PRESSED,
                (event, ctx) -> {
            game.inputHandler.executeKeyboardEvent(event.getCode());
            return UIEventResponse.processed();
        });

        this.getScreen().handleMouseEvents(MouseEventType.MOUSE_PRESSED,
                (event, ctx) -> {
            game.inputHandler.executeMouseEvent(event);
            return UIEventResponse.processed();
        });
    }

    private void setupUnitAndStructureGroup(VBox buttonContainer) {
        var containerWidth = buttonContainer.getContentSize().getWidth();

        var unitAndStructureButtonHolder =
                Components.vbox().withPreferredSize(containerWidth, 5).build();

        var unitAndStructureButtonGroup = Components.radioButtonGroup().build();

        var unitButton = Components.radioButton().withKey("U").withText(
                "Units").withPreferredSize(containerWidth, 2).build();

        var structureButton = Components.radioButton().withKey("S").withText(
                "Structures").withPreferredSize(containerWidth, 2).build();


        List<RadioButton> buttonList = new ArrayList<>();
        buttonList.add(unitButton);
        buttonList.add(structureButton);

        unitAndStructureButtonGroup.addComponent(unitButton);
        unitAndStructureButtonGroup.addComponent(structureButton);

        unitAndStructureButtonHolder.addComponent(unitButton);
        unitAndStructureButtonHolder.addComponent(structureButton);
        buttonContainer.addComponent(unitAndStructureButtonHolder);
    }
}
