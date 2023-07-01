package game.teams;

import org.hexworks.zircon.api.color.TileColor;

public class Team {
    String name;
    TileColor color;

    Team(String name, TileColor color) {
        this.name = name;
        this.color = color;
    }
}
