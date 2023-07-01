package game.world.noise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CellularAutomaton {
    private static List<List<Boolean>> generateNoiseGrid(int width,
                                                         int height,
                                                         int noiseDensity) {
        List<List<Boolean>> noiseGrid = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < height; i++) {
            ArrayList<Boolean> rowArray = new ArrayList<>();

            for (int j = 0; j < width; j++) {
                var noiseVal = rand.nextFloat(100);

                if (noiseVal >= noiseDensity) {
                    rowArray.add(true);
                } else {
                    rowArray.add(false);
                }
            }
            noiseGrid.add(rowArray);
        }

        return noiseGrid;
    }

    public static List<List<Boolean>> generateCellularAutomatonGrid(int width
            , int height, int noiseDensity, int neighborThreshold, int numOfRounds) {
        var currGrid = generateNoiseGrid(width, height, noiseDensity);

        for (int i = 0; i < numOfRounds; i++) {
            List<List<Boolean>> newGrid = new ArrayList<>();

            for (int j = 0; j < height; j++) {
                List<Boolean> rowList = new ArrayList<>();

                for (int k = 0; k < width; k++) {
                    var neighborCount = getActiveNeighborCount(currGrid, k, j);

                    if (neighborCount > neighborThreshold) {
                        rowList.add(true);
                    } else if (neighborCount == neighborThreshold) {
                        rowList.add(currGrid.get(j).get(k));
                    } else {
                        rowList.add(false);
                    }
                }

                newGrid.add(rowList);
            }

            currGrid = newGrid;
        }

        return currGrid;
    }

    private static int getActiveNeighborCount(List<List<Boolean>> grid, int x
            , int y) {
        int neighborCount = 0;

        var gridWidth = grid.get(0).size();
        var gridHeight = grid.size();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                var isOutOfBounds =
                        (i >= gridWidth || i < 0) || (j >= gridHeight || j < 0);

                if (isOutOfBounds || grid.get(j).get(i)) {
                    neighborCount++;
                }
            }
        }

        return neighborCount - 1;
    }
}
