package game.gui;

import game.GAME_CONSTANTS;
import game.Game;
import game.input.InputHandler;
import game.structures.control.StructureControlUpdateCommand;
import game.units.MoveUnitCommand;
import game.world.World;
import game.world.block.TileStore;
import kotlin.Unit;
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

import static game.GAME_CONSTANTS.*;
import static org.hexworks.zircon.api.ComponentDecorations.box;

public class GameView extends BaseView {
    final Game game = new Game();

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

    Panel unitOrStructureListHolderComponent;
    Component unitListComponent;
    Component structureListComponent;

    InputHandler inputHandler;


    public GameView() throws IOException, InterruptedException {
        super(TILE_GRID, COLOR_THEME);

        this.inputHandler = new InputHandler(this);

        var sidePanelContentWidth = this.sidePanel.getContentSize().getWidth();
        var resourceComponent =
                this.game.getPlayer().resourceManager.toComponent(sidePanelContentWidth);

        this.sidePanel.addComponent(resourceComponent);

        // Handle setting up the unit and structure information part of the
        // side-panel
        this.setupUnitAndStructureGroup(this.sidePanel);
        unitListComponent =
                this.game.getUnitStore().toComponent(sidePanelContentWidth);
        structureListComponent =
                this.game.getStructureStore().toComponent(sidePanelContentWidth);
        unitOrStructureListHolderComponent =
                Components.panel().withPreferredSize(unitListComponent.getSize()).build();
        sidePanel.addComponent(unitOrStructureListHolderComponent);


        ComponentRenderer<Panel> gameRenderer =
                GameComponents.newGameAreaComponentRenderer(game.getWorld(),
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

    public Game getGame() {
        return this.game;
    }

    public World getWorld() {
        return this.game.getWorld();
    }

    public void setupInputCallbacks() {
        this.getScreen().handleKeyboardEvents(KeyboardEventType.KEY_PRESSED,
                (event, ctx) -> {
            inputHandler.executeKeyboardEvent(event.getCode());
            return UIEventResponse.processed();
        });

        this.getScreen().handleMouseEvents(MouseEventType.MOUSE_PRESSED,
                (event, ctx) -> {
            inputHandler.executeMouseEvent(event);
            return UIEventResponse.processed();
        });
    }

    public void takeTurn() {
        for (var unit : this.game.getPlayer().unitManager.getUnits()) {
            MoveUnitCommand.executeWithStrategy(game, unit);
        }

        for (var structure :
                this.game.getPlayer().structureManager.getStructures()) {
            StructureControlUpdateCommand.execute(this.game, structure,
                    this.game.getTeam());
        }
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

        unitAndStructureButtonGroup.addComponent(unitButton);
        unitAndStructureButtonGroup.addComponent(structureButton);

        unitAndStructureButtonHolder.addComponent(unitButton);
        unitAndStructureButtonHolder.addComponent(structureButton);
        buttonContainer.addComponent(unitAndStructureButtonHolder);

        // Set up the callbacks for rendering the unit list and structure lists
        // to the screen.
        unitButton.onActivated((event) -> {
            this.unitOrStructureListHolderComponent.detachAllComponents();
            this.unitOrStructureListHolderComponent.addComponent(this.unitListComponent);
            unitButton.clearFocus();

            return Unit.INSTANCE;
        });

        structureButton.onActivated((event) -> {
            this.unitOrStructureListHolderComponent.detachAllComponents();
            this.unitOrStructureListHolderComponent.addComponent(this.structureListComponent);
            structureButton.clearFocus();

            return Unit.INSTANCE;
        });
    }

}
