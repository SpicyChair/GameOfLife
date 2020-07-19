
import model.*;

public class Main {

    //the size of the universe
    //change this to whatever you want
    final static int size = 40;
    static Universe universe = new Universe(size);
    static GameOfLife game = new GameOfLife(size);

    public static void main(String[] args) {
        
        //initialise the field
        reset();

        while (true) {
            if (game.isPaused) {
                sleep(200);
            } else if (game.resetPressed) {
                game.resetPressed = !game.resetPressed;
                reset();
                sleep(game.speed);
            } else {
                refresh();
                sleep(game.speed);
            }
        }
    }
    public static void reset() {
        game.initialiseField();
        universe.generateInitial();
    }

    public static void refresh() {
        Evolution.evolveOneGeneration(universe);
        game.updateField(universe);
        game.updateStats(universe.getGenerationNumber(), universe.getAliveCells());
        game.repaint();
    }
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }    
}
