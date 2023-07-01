package game.player;

import game.common.Updatable;
import game.resources.ResourceManager;
import game.structures.StructureManager;
import game.units.UnitManager;

public class Player implements Updatable {
    public ResourceManager resourceManager;
    public UnitManager unitManager;
    public StructureManager structureManager;

    public Player() {
        this.resourceManager = new ResourceManager();
        this.unitManager = new UnitManager();
        this.structureManager = new StructureManager();
    }

    public void update() {

    }
}
