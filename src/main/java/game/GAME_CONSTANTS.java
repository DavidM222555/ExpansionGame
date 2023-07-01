package game;

import org.hexworks.zircon.api.CP437TilesetResources;
import org.hexworks.zircon.api.ColorThemes;
import org.hexworks.zircon.api.SwingApplications;
import org.hexworks.zircon.api.application.AppConfig;
import org.hexworks.zircon.api.component.ColorTheme;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.resource.TilesetResource;

public class GAME_CONSTANTS {
    public final static int WINDOW_WIDTH = 80;
    public final static int WINDOW_HEIGHT = 45;
    public final static TilesetResource TILESET =
            CP437TilesetResources.rogueYun16x16();

    public final static TileGrid TILE_GRID =
            SwingApplications.startTileGrid(AppConfig.newBuilder().withDefaultTileset(TILESET).withSize(WINDOW_WIDTH, WINDOW_HEIGHT).withTitle("EXPANSION").build());
    public final static ColorTheme COLOR_THEME = ColorThemes.monokaiOrange();

    public final static int SIDEBAR_WIDTH = 20;
    public final static int SIDEBAR_HEIGHT = 45;

    public final static int RESOURCES_LABEL_HEIGHT = 5;

    public final static int STRUCTURE_AND_UNIT_RADIO_GROUP_HEIGHT = 10;

    public final static int LOGAREA_WIDTH = 60;
    public final static int LOG_AREA_HEIGHT = 10;

    public final static int GAME_AREA_WIDTH = 60;
    public final static int GAME_AREA_HEIGHT = 35;

    // The main area consists of the log area and the game screen
    public final static int MAIN_AREA_WIDTH = LOGAREA_WIDTH;
    public final static int MAIN_AREA_HEIGHT =
            LOG_AREA_HEIGHT + GAME_AREA_HEIGHT;

    public final static int GAME_X = 700;
    public final static int GAME_Y = 250;
    public final static int GAME_Z = 1;

    public final static int VISIBLE_GAME_X = GAME_AREA_WIDTH;
    public final static int VISIBLE_GAME_Y = GAME_AREA_HEIGHT;

    public final static int VISIBLE_GAME_Z = GAME_Z;
}
