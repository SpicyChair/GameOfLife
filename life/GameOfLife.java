//package life;
import model.*;
import javax.swing.*;
import java.awt.*;

 
public class GameOfLife extends JFrame {

    private static final long serialVersionUID = 1L;

    // the field where cells are
     JPanel field;
     //state of cells
     JPanel[][] cells;

     //contains stats and controls
     JPanel controlPanel;
     JLabel generationLabel;
     JLabel aliveLabel;

     JToggleButton pauseButton;
     JButton resetButton;
     //JSlider speedSlider;
     
     //flags for pause and reset
     boolean isPaused;
     boolean resetPressed;

     int size;

    public GameOfLife(int size) {
        //setting up JFrame
        super("Game Of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(912, 737);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        //setMinimumSize(getSize());



        //initialise stats/control panel

        controlPanel  = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(200,737));
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
            controlPanel.getBorder(), 
            BorderFactory.createEmptyBorder(10, 10, 10, 5)));  
        
        //intialise stats labels

        generationLabel = new JLabel("Generation # ");
        generationLabel.setFont(new Font("Sans", Font.BOLD, 20));
        generationLabel.setBounds(40, 20, 100, 30);
    
        aliveLabel = new JLabel("Alive: ");
        aliveLabel.setFont(new Font("Sans", Font.BOLD, 20));
        aliveLabel.setBounds(40, 20, 100, 30);

        

        //initialise reset and pause buttons

        resetButton = new JButton("Reset");
        resetButton.setBounds(40, 20, 100, 50);
        resetButton.addActionListener(e -> resetPressed = !resetPressed);

        pauseButton = new JToggleButton("Pause / Unpause");
        pauseButton.setBounds(40, 20, 100, 50);
        pauseButton.addActionListener(e -> isPaused = !isPaused);

        //add stats / control panel
        controlPanel.add(aliveLabel);
        controlPanel.add(generationLabel);
        //controlPanel.add(resetButton);
        controlPanel.add(pauseButton);
        add(controlPanel, BorderLayout.WEST);

        //intialise field of cells

        field = new JPanel();
        add(field, BorderLayout.CENTER);

        this.size = size;
        setVisible(true);
    }


    public void initialiseField() {
        //create an array of cells
        cells = new JPanel[size][size];
        for (int i = 0; i != size; i++) {
            for (int j = 0; j != size; j++) {
                cells[i][j] = new JPanel();
                cells[i][j].setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
                //add them to the field
                field.add(cells[i][j]);
            } 
        }
        setVisible(true);
    }

    public void updateField(Universe u) {
        //hide field for updating
        field.setVisible(false);
        //get cell states from the model
        Boolean[][] map = u.getMap();

        for (int i = 0; i != size; i++) {
            for (int j = 0; j != size; j++) {
                if (map[i][j]) {
                    //if alive
                    cells[i][j].setBackground(Color.BLACK);
                } else {
                    //if dead
                    cells[i][j].setBackground(Color.WHITE);
                }
            }
        }
        //re-show the universe
        field.setVisible(true);
    }

    public void updateStats(int gen, int alive) {
        //update JLabels
        generationLabel.setText("Generation #" + gen);
        aliveLabel.setText("Alive: " + alive);
    }
}