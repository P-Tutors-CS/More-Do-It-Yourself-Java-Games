
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Framed extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int GRIDSIZE = 3;

    private LightButton[][] lightButton = new LightButton[GRIDSIZE][GRIDSIZE];

    private void toggleLights(int row, int col) {
        lightButton[row][col].toggle();

        // top left corner
        if (row == 0 && col == 0) {
            lightButton[row][col + 1].toggle();
            lightButton[row + 1][col].toggle();
            lightButton[row + 1][col + 1].toggle();
        }
        // top right corner
        else if (row == 0 && col == 2) {
            lightButton[row][col - 1].toggle();
            lightButton[row + 1][col].toggle();
            lightButton[row + 1][col - 1].toggle();
        }
        // bottom left corner
        else if (row == 2 && col == 0) {
            lightButton[row][col + 1].toggle();
            lightButton[row - 1][col].toggle();
            lightButton[row - 1][col + 1].toggle();
        }
        // bottom right corner
        else if (row == 2 && col == 2) {
            lightButton[row][col - 1].toggle();
            lightButton[row - 1][col].toggle();
            lightButton[row - 1][col - 1].toggle();
        }
        // top row middle
        else if (row == 0 && col == 1) {
            lightButton[row][col - 1].toggle();
            lightButton[row][col + 1].toggle();
        }
        // bottom row middle
        else if (row == 2 && col == 1) {
            lightButton[row][col - 1].toggle();
            lightButton[row][col + 1].toggle();
        }
        // left sie middle
        else if (row == 1 && col == 0) {
            lightButton[row - 1][col].toggle();
            lightButton[row + 1][col].toggle();
        }
        // right side middle
        else if (row == 1 && col == 2) {
            lightButton[row - 1][col].toggle();
            lightButton[row + 1][col].toggle();
        }
        // center
        else if (row == 1 && col == 1) {
            lightButton[row][col - 1].toggle();
            lightButton[row][col + 1].toggle();
            lightButton[row - 1][col].toggle();
            lightButton[row + 1][col].toggle();
        }
    }

    private void endGameifDone() {
        boolean done = lightButton[0][0].isLit() 
        && lightButton[0][1].isLit() 
        && lightButton[0][2].isLit() 
        && lightButton[1][0].isLit() 
        && !lightButton[1][1].isLit() 
        && lightButton[1][2].isLit() 
        && lightButton[2][0].isLit() 
        && lightButton[2][1].isLit() 
        && lightButton[2][2].isLit() ;

        if (done){
            String message = "Congratulations! You won! Do you want to play again?";
            int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION){
                newGame();
            }else{
                System.exit(0);
            }
        }
    }

    private void initGUI() {
        JLabel titleLabel = new JLabel("Framed");
        add(titleLabel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
        add(centerPanel, BorderLayout.CENTER);

        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                lightButton[row][col] = new LightButton(row, col);
                lightButton[row][col].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        LightButton button = (LightButton) e.getSource();
                        int row = button.getRow();
                        int col = button.getCol();
                        toggleLights(row, col);
                        // check if game is over
                        endGameifDone();
                    }


                });
                centerPanel.add(lightButton[row][col]);
            }
        }
    }

    private void newGame() {
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                lightButton[row][col].turnOn();
            }
        }
        lightButton[1][1].toggle();

        Random rand = new Random();
        int numberOfTimes = rand.nextInt(10) + 10;

        for (int i = 0; i < numberOfTimes; i++) {
            int row = rand.nextInt(GRIDSIZE);
            int col = rand.nextInt(GRIDSIZE);
            toggleLights(row, col);
        }
    }

    public Framed() {
        initGUI();

        setTitle("Framed");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newGame();
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Framed();
            }
        });
    }
}
