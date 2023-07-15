package game.resources;

import org.hexworks.cobalt.databinding.api.extension.Properties;
import org.hexworks.cobalt.databinding.api.property.PropertyDelegate;
import org.hexworks.zircon.api.Components;
import org.hexworks.zircon.api.component.Component;
import org.hexworks.zircon.api.component.Label;
import org.hexworks.zircon.api.component.VBox;
import org.hexworks.zircon.api.graphics.BoxType;

import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.ComponentDecorations.margin;


public class ResourceManager {
    Gold gold;
    Iron iron;
    Wood wood;

    PropertyDelegate<String> resourceString;
    PropertyDelegate<String> goldStringProperty;
    PropertyDelegate<String> ironStringProperty;
    PropertyDelegate<String> woodStringProperty;


    public ResourceManager() {
        this.gold = new Gold(0);
        this.iron = new Iron(0);
        this.wood = new Wood(0);

        this.resourceString = Properties.toProperty(String.format("G:%4d," +
                "I:%4d," + "W:%4d", 0, 0, 0, 0)).asDelegate();

        this.goldStringProperty = Properties.toProperty(String.format("GOLD: "
                + "%-6d", 0)).asDelegate();

        this.ironStringProperty = Properties.toProperty(String.format("IRON: "
                + "%-6d", 0)).asDelegate();

        this.woodStringProperty = Properties.toProperty(String.format("WOOD: "
                + "%-6d", 0)).asDelegate();
    }

    ResourceManager(int goldAmount, int ironAmount, int woodAmount) {
        this.gold = new Gold(goldAmount);
        this.iron = new Iron(ironAmount);
        this.wood = new Wood(woodAmount);
    }

    public int getGoldAmount() {
        return this.gold.getCurrentAmount();
    }

    public int getIronAmount() {
        return this.iron.getCurrentAmount();
    }

    public int getWoodAmount() {
        return this.wood.getCurrentAmount();
    }

    public void changeGoldAmount(int dx) {
        this.gold.changeCurrentAmount(dx);

        this.goldStringProperty.setValue(String.format("GOLD: " + "%-6d",
                this.gold.amount));
    }

    public void changeIronAmount(int dx) {
        this.iron.changeCurrentAmount(dx);
        this.ironStringProperty.setValue(String.format("IRON: " + "%-6d",
                this.iron.amount));
    }

    public void changeWoodAmount(int dx) {
        this.wood.changeCurrentAmount(dx);
        this.woodStringProperty.setValue(String.format("WOOD: " + "%-6d",
                this.wood.amount));
    }

    public void changeResourceAmounts(int goldDx, int ironDx, int woodDx) {
        this.changeGoldAmount(goldDx);
        this.changeIronAmount(ironDx);
        this.changeWoodAmount(woodDx);
    }

    public Component toComponent(int width) {
        VBox resourcesComponent = Components.vbox().withPreferredSize(width,
                6).withDecorations(box(BoxType.SINGLE, "Resources"), margin(0
                , 0, 1, 0)).build();

        Label goldLabel =
                Components.label().withPreferredSize(resourcesComponent.getContentSize().getWidth(), 1).build();

        goldLabel.getTextProperty().updateFrom(this.goldStringProperty, true);

        Label ironLabel =
                Components.label().withPreferredSize(resourcesComponent.getContentSize().getWidth(), 1).build();

        ironLabel.getTextProperty().updateFrom(this.ironStringProperty, true);

        Label woodLabel =
                Components.label().withPreferredSize(resourcesComponent.getContentSize().getWidth(), 1).build();

        woodLabel.getTextProperty().updateFrom(this.woodStringProperty, true);

        resourcesComponent.addComponent(goldLabel);
        resourcesComponent.addComponent(ironLabel);
        resourcesComponent.addComponent(woodLabel);

        return resourcesComponent;
    }
}
