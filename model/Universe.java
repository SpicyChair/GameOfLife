package model;
import java.util.Random;

public class Universe {

    private Boolean[][] map;
    private int size;
    private int generation;

    public Universe(int size) {
        this.size = size;
        this.map = new Boolean[size][size];
    }

    public void generateInitial() {
        Random r = new Random();
        for (int i = 0; i != size; i++) {
            for (int j = 0; j != size; j++) {
                this.map[i][j] = r.nextBoolean();
            }
        }
    }

    public void setNextGeneration(Boolean[][] generation) {
        this.map = generation;
        this.generation++;
    }
    public int getGenerationNumber() {
        return this.generation;
    }

    public Boolean[][] getMap() {
        return this.map;
    }

    public int getAliveCells() {
        int count = 0;
        for (int i = 0; i != size; i++) {
            for (int j = 0; j != size; j++) {
                if (this.map[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Deprecated
    public void printUniverse() {
        for (int i = 0; i != size; i++) {
            for (int j = 0; j != size; j++) {
                if (this.map[i][j]) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
