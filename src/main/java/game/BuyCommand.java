package game;

import game.player.Player;
import game.structures.BuyStructureCommand;
import game.structures.StructureStore;
import game.units.BuyUnitCommand;
import game.units.UnitStore;
import org.hexworks.zircon.api.uievent.KeyCode;

public class BuyCommand {
    public static void execute(KeyCode kc, Game game, Player player) {
        // Simplistic method currently for handling the differentiation between
        // buying structures and buying units. Eventually should move to
        // something more robust.
        Character charForKey = kc.toCharOrNull();

        if (charForKey == null) {
            return;
        }

        if (UnitStore.containsKey(charForKey)) {
            // System.out.println("Buying a unit");
            BuyUnitCommand.execute(kc, game, player);
        } else if (StructureStore.containsKey(charForKey)) {
            // System.out.println("Buying a structure");
            BuyStructureCommand.execute(kc, game, player);
        }
    }
}
