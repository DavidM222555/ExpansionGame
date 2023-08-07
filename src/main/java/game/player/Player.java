package game.player;

import game.Game;
import game.resources.ResourceManager;
import game.structures.HandleResourceMiningCommand;
import game.structures.ResourceStructure;
import game.structures.StructureManager;
import game.structures.control.StructureControlUpdateCommand;
import game.structures.vision.StructureVisionUpdateCommand;
import game.teams.Team;
import game.units.MoveUnitCommand;
import game.units.Unit;
import game.units.UnitManager;

public class Player {
    public ResourceManager resourceManager;
    public UnitManager unitManager;
    public StructureManager structureManager;

    public Team team;

    public Player(Team team) {
        this.resourceManager = new ResourceManager();
        this.unitManager = new UnitManager();
        this.structureManager = new StructureManager();
        this.team = team;

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

    public Team getTeam() {
        return this.team;
    }

    /**
     * Iterates over all structures and all units the player controls
     * and performs legal operations for each.
     */
    public void update(Game game) {
        for (var unit : this.unitManager.getUnits()) {
            MoveUnitCommand.executeWithStrategy(game, unit);
        }

        for (var structure : this.structureManager.getStructures()) {
            StructureControlUpdateCommand.execute(game, structure, team);

            StructureVisionUpdateCommand.execute(game, structure);

            if (structure instanceof ResourceStructure) {
                var gameBlockAtPos =
                        game.getBlockAtPosOrNull(structure.getPos());

                gameBlockAtPos.ifPresent(gameBlock -> HandleResourceMiningCommand.execute(this, gameBlock));
            }
        }
    }
}
