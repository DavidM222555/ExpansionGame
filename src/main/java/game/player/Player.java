package game.player;

import game.common.Updatable;
import game.resources.ResourceManager;
import game.structures.StructureManager;
import game.units.Unit;
import game.units.UnitManager;

public class Player implements Updatable {
    public ResourceManager resourceManager;
    public UnitManager unitManager;
    public StructureManager structureManager;

    public Player() {
        this.resourceManager = new ResourceManager();
        this.unitManager = new UnitManager();
        this.structureManager = new StructureManager();

        this.resourceManager.changeResourceAmounts(500, 500, 500);
    }

    public void updateResources(int goldDx, int ironDx, int woodDx) {
        this.resourceManager.changeResourceAmounts(goldDx, ironDx, woodDx);
    }

    public int getGoldAmount() {
        return this.resourceManager.getGoldAmount();
    }

    public int getIronAmount() {
        return this.resourceManager.getIronAmount();
    }

    public int getWoodAmount() {
        return this.resourceManager.getWoodAmount();
    }

    public void addUnit(Unit unit) {
        this.unitManager.addUnit(unit);
    }

    /**
     * Iterates over all structures and all units the player controls
     * and performs legal operations for each.
     */
    public void update() {
    }
}
