package game.player;

import game.teams.Team;
import org.hexworks.zircon.api.color.TileColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PlayerManager {
    private final Map<String, Player> playerMap;

    private PlayerManager() {
        playerMap = new HashMap<>();
    }

    public static PlayerManager fromNumberOfPlayers(int numberOfPlayers) throws FileNotFoundException {
        PlayerManager playerManager = new PlayerManager();

        File playerFile = new File("src/mainassets/players.txt");
        var playerFileReader = new Scanner(playerFile);

        while (playerFileReader.hasNextLine()) {
            var splitString = playerFileReader.nextLine().split(",");
            var playerName = splitString[0];
            var playerColor = TileColor.fromString(splitString[1]);

            playerManager.playerMap.put(playerName,
                    new Player(new Team(playerName, playerColor)));
        }

        return playerManager;
    }

    public Player getPlayer(String name) {
        return this.playerMap.get(name);
    }

    public List<Player> getPlayers() {
        return (List<Player>) this.playerMap.values();
    }
}
