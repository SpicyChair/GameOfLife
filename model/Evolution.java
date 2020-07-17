package model;

import java.util.Arrays;

public class Evolution {

    public static void evolveOneGeneration(Universe universe) {
        int size = universe.getMap().length;
        Boolean[][] currentGeneration = Arrays.copyOf(universe.getMap(), size);
        Boolean[][] nextGeneration = new Boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int numOfNeighbours = checkAdjacent(i, j, currentGeneration, size);

                if (currentGeneration[i][j]) { // alive
                    nextGeneration[i][j] = (numOfNeighbours >= 2 && numOfNeighbours <= 3);
                } else { // dead
                    nextGeneration[i][j] = numOfNeighbours == 3;
                }
            }
        }
        universe.setNextGeneration(nextGeneration);
    }

    private static int checkAdjacent(int rowNumber, int columnNumber, Boolean[][] currentGeneration, int size) {
        int numOfNeighbors = 0;
        for (int i = rowNumber - 1; i <= rowNumber + 1; i++) {
            for (int j = columnNumber - 1; j <= columnNumber + 1; j++) {
                if (currentGeneration[i < 0 ? size - 1 : i == size ? 0 : i][j < 0 ? size - 1 : j == size ? 0 : j]) {
                    numOfNeighbors++;
                }
            }
        }
        if (currentGeneration[rowNumber][columnNumber]) {
            numOfNeighbors--; //decrement as the loop counts current point
        }
        return numOfNeighbors;
    }
}