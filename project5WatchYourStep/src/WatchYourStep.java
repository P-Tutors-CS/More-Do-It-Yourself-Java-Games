
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Project 5 - Watch Your Step
 * Next you'll create the Watch Your Step game. The Watch
 * Your Step game consists of a grid of tiles in IO rows and
 * 10 columns. Hidden behind ten of the tiles are black holes.
 * Hidden behind some of the other tiles is the number of
 * black holes in neighboring tiles. Click a tile to reveal what
 * is behind it. If that tile has no number, the game will also
 * reveal that tile's neighbors. The goal is to reveal all the tiles
 * without clicking on a black hole.
 * 
 */

public class WatchYourStep extends JFrame {
    private static final long serialVersionUID = 1L;

    // Add a private static final integer instance variable called GRIDSIZE, set to
    // 10.
    private static final int GRIDSIZE = 10;
    // Add a private static final integer instance variable called NUMBEROFHOLES,
    // set to 10.
    private static final int NUMBEROFHOLES = 10;
    // Add a private TerrainButton two- dimensional array instancevariable called
    // terrain, with GRIDSIZE rows and GRIDSIZE columns.
    private TerrainButton[][] terrain = new TerrainButton[GRIDSIZE][GRIDSIZE];
    // Add a private integer instance variable called totalRevealed,initialized to
    // O.
    private int totalRevealed = 0;

    /**
     * Create a new private method called initGUI().
     * It should take no parameters and return nothing. In initGUI():
     */
    private void initGUI() {
        // Create a new TitleLabel object called titleLabel, with text "Watch Your
        // Step".
        JLabel titlelabel = new JLabel("Watch Your Step");
        // Add titleLabel to the top of the window. (Hint: use JFrame's add() method and
        // BorderLayoutIs PAGE_START variable.)
        add(titlelabel, BorderLayout.PAGE_START);

        // create a JPanel object called centerPanel.
        JPanel centerJPanel = new JPanel();
        // Set the layout for centerPaneI to a new GridLayout, GRIDSIZE wide and
        // GRIDSIZE high. (Hint: use JPaneI's setLayout() method.)
        centerJPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
        // Add centerPanel to the center of the window. (Hint: use JPane1is add() method
        // and BorderLayout's CENTER variable.)
        add(centerJPanel, BorderLayout.CENTER);

        // Use a double for loop to go through each terrain, using integers row and col
        // as iteration variables for the for loops:
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                // Create a new TerrainButton object in terrain at row and col. (Hint: use row
                // and col as parameter values to the constructor.)
                terrain[row][col] = new TerrainButton(row, col);

                // 1. Add an ActionListener to each terrain element as that terrain object is
                // created in initGUI(). (Hint: use
                // TerrainButton's addActionListener() method, with a new ActionListener object
                // as the parameter value.
                // In the ActionListener, add a public method called actionPerformed().
                // actionPerformed() should have
                // one parameter, an ActionEvent called e, and return nothing.)
                terrain[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 2. In actionPerformed(), create a TerrainButton object called button,
                        // initialized by getting the source of
                        // e. (Hint: use ActionEvent's getSource() method. Hint: use Quick Fix to cast
                        // the returned value to a
                        // TerrainButton.)
                        TerrainButton button = (TerrainButton) e.getSource();

                        // 3. Create integers called row and col, initialized by getting the row and
                        // column values from button,
                        // respectively. (Hint: use TerrainButtonisgetRow() and getCol() methods.)
                        // 4. Call clickedTerrain() with row and col.
                        int row = button.getRow();
                        int col = button.getCol();
                        clickedTerrain(row, col);

                    }
                });

                // Add the terrain element at row and col to centerPanel. (Hint: use JPane1's
                // add() method.)
                centerJPanel.add(terrain[row][col]);

            }
        }

    }

    // check() will need to reveal what is hidden behind the terrain button if the
    // terrain element exists and
    // its contents is not yet revealed.
    // 1. Add a private method called check(). It should take two parameters,
    // integers called row and col, and
    // return nothing.
    // 2. If row and col are valid, that terrain does not have a hole, and that
    // terrain is not yet revealed:
    // a. Reveal the terrain button. (Hint: use TerrainButton's reveal() method.)
    private void check(int row, int col) {
        if (row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE
                && !terrain[row][col].hasHole()
                && !terrain[row][col].isRevealed()) {
            terrain[row][col].reveal(true);
            // The game is lost if a terrain button that has a hole is clicked. However, the
            // game
            // must also check if the user won.
            // First, WatchYourStep has to keep track of how many terrain buttons have been
            // revealed.
            // 1. Add code to check() to increment totalRevealed every time a terrain is
            // revealed.
            totalRevealed++;

            if (!terrain[row][col].isNextToHoles()) {
                checkNeighbors(row, col);
            }
        }
    }

    // To start a new game, all the terrain buttons need to be reset to their
    // original,
    // default values and a new random selection of terrain buttons with holes must
    // be made.
    // 1. Add a private method called newGame(). It should take no parameters and
    // return nothing.
    // 2. Loop through all the terrain buttons in terrain and reset them. (Hint: use
    // a
    // double for loop and TerrainButton's reset() method.)
    // 3. Call setHoles() to choose the placement of holes for the new game.
    private void newGame() {
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                terrain[row][col].reset();
            }
        }
        setHoles();
        totalRevealed = 0;
    }

    // 1. Add a private method called showHoles(). It should take no parameters and
    // return nothing.
    // 2. In showHoles(), loop through each terrain. If the terrain has a hole,
    // reveal
    // the terrain. (Hint: use TerrainButton's hasHole() and reveal() methods.)
    private void showHoles() {
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                if (terrain[row][col].hasHole()) {
                    terrain[row][col].reveal(true);
                }
            }
        }
    }

    // Display a confirm dialog with the message that asks if the user wants to play
    // again. If the user clicks Yes,
    // start a new game. Otherwise, end the program.
    // 1. Add a private method called promptForNewGame(). It should take a string
    // parameter called message
    // and return nothing.
    // 2. Create an integer called option that gets its value by displaying message
    // in a confirm dialog
    // with Yes and No buttons. (Hint: use JOptionPane's showConfirmDialog() method,
    // with JOptionPane's
    // variable.)
    // 3. If option has a yes value, call newGame(). (Hint: compare option with
    // JOptionPane's YES_OPTION
    // variable.)
    // 4. Otherwise, exit the program. (Hint: use System's exit() method, with O for
    // its parameter value.)
    private void promptForNewGame(String message) {
        // Try It - Reveal All Holes at the End of the Game
        // One more thing that would be nice for WatchYourStep to do would be to reveal
        // all the remaining terrains that have holes at the end of a game.
        // I. Add code to promptForNewGame() to call showHoIes() before displaying the
        // message.
        showHoles();

        int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            System.exit(0);
        }
    }

    public void clickedTerrain(int row, int col) {
        // Try It - End the Game When a Hole is Clicked
        // Next, end the game if the user clicks on a terrain button that has a hole.
        // 1. Add code to the beginning of clickedTerrain() to check whether the clicked
        // terrain has a hole. (Hint:
        // check the terrain at row and col. Hint: use TerrainButton's hasHole()
        // method.)
        // 2. If the terrain has a hole, create a string called message, stating that
        // the user stepped in a hole and the
        // game is over and asking the user if he wants to play again.
        // 3. Call promptForNewGame(), passing the message as a parameter value.
        // 4. Put the rest of the code in that method in an else block so the code only
        // checks that terrain button and
        // that button's neighbors if terrain does not have a hole.
        if (terrain[row][col].hasHole()) {
            String message = "You stepped on a hole. Game Over.\nDo you want to play agin? ";
            promptForNewGame(message);

        } else {
            check(row, col);
            checkNeighbors(row, col);

            // Every time the code reveals a terrain button, WatchYourStep will need to
            // check
            // whether all terrain buttons without holes have been revealed. If all the
            // buttons
            // without holes have been revealed, congratulate the user and ask if he wants
            // to
            // play again. How can WatchYourStep check whether all terrain buttons without
            // holes have been revealed? Subtract the number of holes from the total number
            // of buttons and compare that with the number of buttons that have been
            // revealed.
            // 1. In clickedTerrain, after checking neighbors, check whether all the terrain
            // buttons without holes have been revealed. (Hint: use GRIDSIZE and
            // NUMBEROFHOLES to calculate the number of non-hole terrain buttons.)
            // 2. If all terrain buttons without holes have been revealed, create a string
            // called message, telling the user he won, and ask if he wants to play again.
            // (Hint: call promptForNewGame(), with message as a parameter value.)
            if (totalRevealed == GRIDSIZE * GRIDSIZE - NUMBEROFHOLES) {
                String message = "You won! Do you want to play again?";
                promptForNewGame(message);
            }
        }

    }

    // Next, WatchYourStep will need to check whether to reveal any of the clicked
    // terrain button's eight
    // neighbors.
    // 1. Add a private method called checkNeighbors(). It should take two
    // parameters, integers called row and
    // cot, and return nothing.
    // 2. Call check() eight times, once for each potential terrain surrounding row
    // and col. (Hint: the terrain up
    // and to the left of row and col would be row-I and col-l. Above row and col
    // would be row-I and col. Hint:
    // check() will ignore any row and col values that aren't valid rows or
    // columns.)
    private void checkNeighbors(int row, int col) {
        check(row - 1, col - 1);
        check(row - 1, col);
        check(row - 1, col + 1);
        check(row, col - 1);
        check(row, col + 1);
        check(row + 1, col - 1);
        check(row + 1, col);
        check(row + 1, col + 1);
    }

    // Add a private method called addToHoleCount(). It should take two parameters,
    // integers called row and
    // col, and return nothing.
    private void addToHoleCount(int row, int col) {
        // If row and col refer to a valid neighbor, increase the hole count for that
        // terrain element. (Hint: row and
        // col refer to a valid element if both are greater than -1 and less than
        // GRIDSIZE. Hint: use TerrainButton's
        // increaseHoleCount() method.)
        // After increasing the hold count, reveal that terrain element so you can see
        // how the program is working.
        if (row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE) {
            terrain[row][col].increaseHoleCount();

            /*
             * Clicking on any terrain button not yet revealed should reveal any of its
             * immediate neighbors that don't
             * have a hole. And if that neighbor is not next to terrain button with a hole,
             * the program should reveal any of
             * that neighbor's neighbors that don't have a hole.
             * Try It - Hide All Terrain Information
             * You will no longer need to see the holes and hole counts.
             * Remove the line of code that reveals the terrain in addToHoleCount().
             */
            // terrain[row][col].reveal(true);
        }

    }

    // When a hole is added to a terrain on the grid, WatchYourStep will need to
    // update the hole count for as many
    // as eight neighbors of that terrain button. But because edge terrain buttons
    // don't have eight neighbors,
    // WatchYourStep will need to check whether each neighbor exists before trying
    // to update the neighbor's
    // hole count. Therefore, WatchYourStep will use one method to try all eight
    // possible neighbors, and another
    // method to make sure the neighbor exists before updating the neighbor.
    // Add a private method called addToNeighborsHoleCount(). It should take two
    // parameters, integers called row and col, and return nothing.
    private void addToNeighborsHoleCount(int row, int col) {
        // Call addToHoleCount() eight times, once for each possible terrain surrounding
        // row and col. (Hint: the
        // terrain up and to the left of row and col would be row-I and col-l. Above the
        // same terrain would be
        // row-I and col. Hint: addToHoleCount() will ignore any invalid rows and
        // columns.)
        addToHoleCount(row - 1, col - 1);
        addToHoleCount(row - 1, col);
        addToHoleCount(row - 1, col + 1);
        addToHoleCount(row, col - 1);
        addToHoleCount(row, col + 1);
        addToHoleCount(row + 1, col - 1);
        addToHoleCount(row + 1, col);
        addToHoleCount(row + 1, col + 1);
    }

    // Add a private method called setHoles(). It should take no parameters and
    // return nothing.
    private void setHoles() {

        // Create a new Random object called rand for generating a random number.
        Random rand = new Random();

        // Add a loop to repeat for the number ofholes to be created: (Hint: use a for
        // loop and NUMBEROFHOLES.)
        for (int i = 0; i < NUMBEROFHOLES; i++) {
            // Create two integers called pickRow and pickCol, each initialized by getting a
            // different random
            // number from rand. (Hint: use Random's nextlnt() method. Hint: pick a number
            // between O and one less than GRIDSIZE.)
            int pickRow = rand.nextInt(GRIDSIZE);
            int pickCol = rand.nextInt(GRIDSIZE);

            // While the terrain element at pickRow and pickCol has a hole, use rand to pick
            // new random numbers
            // for pickRow and pickCol. (Hint: use TerrainButton's hasHole() method and
            // Random's nextlnt()
            // method.)
            while (terrain[pickRow][pickCol].hasHole()) {
                pickRow = rand.nextInt(GRIDSIZE);
                pickCol = rand.nextInt(GRIDSIZE);
            }
            // After the while loop, set the hole for the terrain element at pickRow and
            // pickCol to true. (Hint: use
            // TerrainButton's setHole() method.)
            terrain[pickRow][pickCol].setHole(true);

            addToNeighborsHoleCount(pickRow, pickCol);

            // Reveal the terrain element just set to a hole, so you can see whether your
            // code really hides holes in
            // 10 squares. (Hint: use TerrainButton's reveal() method.)
            // terrain[pickRow][pickCol].reveal(true); // . Remove the line of code within
            // setHoles() that reveals a terrain that has a hole.

        }
    }

    public WatchYourStep() {
        // call initGU1().
        initGUI();
        setHoles();
        // Set the window's title to "Watch Your Step",
        setTitle("Watch Your Step");
        // disable resizing
        setResizable(false);
        // pack the window,
        pack();
        // center the window,
        setLocationRelativeTo(null);
        // make the window visible,
        setVisible(true);
        // set the window to exit when closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) throws Exception {

        // Add code to main() to use the cross platform look and feel
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
        }

        // create a new WatchYourStep object on the event queue.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WatchYourStep();
            }
        });
    }
}
