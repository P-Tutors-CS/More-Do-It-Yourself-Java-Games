package ptutorscs.java.doityourself;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/* 
Project 7 - Maze Generator and Anti-Maze
Next you'll create a Maze Generator game, which will generate a random maze. Maze
Generator will respond to cursor movement keys to move a ball from the top left corner of the
maze to the bottom right corner. 

Anti-Maze is Maze Generator with a slight change: you may only move through the maze by
crossing a line. You cannot cross open space.
*/
public class MazeGenerator extends JFrame {
    /*
     * 4. Use Quick Fix to add a private static final long instance variable called
     * serialVersionUID,
     * set to IL.
     * 5. Add a private TitleLabe1 instance variable called titleLabel, with text
     * "Maze".
     * 6. Add aMazeGenerator constructor with no parameters. In the constructor:
     * a. call initGU1().
     * b. Set the window's title to "Maze Generator", disable resizing, pack the
     * window, center
     * the window, make the window visible, and set the window to exit when closed.
     * 7. Create a new private method called initGUI(). It should take no parameters
     * and return
     * nothing. In initGUI(), add titleLabeI to the top of the window.
     * 8. Add code to main() to use the cross platform look and feel and to create a
     * new
     * MazeGenerator object on the event queue.
     */
    private static final long serialVersionUID = 1L;
    private JLabel titLabel = new JLabel("Maze Generator");

    /*
     * Try It - Put a Grid of Cells with
     * Walls in the Window
     *
     * update MazeGenerator to now add a grid of cells with 5 rows and 5 columns
     * 
     * 1. Go back to MazeGenerator.java.
     * 2. Add private integer instance variables called rows and
     * cols, each initialized to 5.
     * 3. Create a private Cell two-dimensional array instance
     * variable called cell, with rows rows and cols columns.
     * 4. Create a private JPanel instance variable called
     * mazePanel, initialized by calling JPanel's constructor.
     */
    private int rows = 5;
    private int cols = 5;
    Cell[][] cell = new Cell[rows][cols];
    private JPanel mazePanel = new JPanel();

    /*
     * MazeGenerator still doesn't paint any balls. That's because
     * MazeGenerator hasn't yet set the current and end positions
     * of the maze. First create the variables to hold the values.
     * 1. Go back to MazeGenerator.java.
     * 2. Add two private integer instance variables called row
     * and col, each initialized to O.
     * 3. Add two private integer instance variables called
     * endRow and endCol, initialized to one less than the
     * values of rows and cols, respectively.
     */
    private int row = 0;
    private int col = 0;
    private int endRow = rows - 1;
    private int endCol = cols - 1;

    public MazeGenerator() {
        initGUI();

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /*
     * 1. In the center panel section Of initGUI(), create a JPaneI
     * object called centerPanel.
     * 2. Set the background color of centerPanel to black. (Hint:
     * use JPanel's setBackground() method.)
     * 3. Add centerPanel to the center of the window. (Hint:
     * use JFrame's add() method and BorderLayout's CENTER
     * variable.)
     * 4. In the maze panel section of initGUI(), call newMaze().
     * 5. Add mazePanel to centerPanel. (Hint: use JPanel's add()
     * method.)
     * 
     * Try It - Listen for Keystrokes
     * Next you'll add code to move the green ball through the
     * maze.
     * To make MazeGenerator listen for keystrokes from the
     * keyboard, add a key listener. Adding key listeners is very
     * similar to adding action listeners.
     * Instead of adding the key listener to a button, you'll add the
     * key listener to the window itself (the JFrame).
     * 1. In the listeners section of initGUI(), add a key listener to
     * the window by inserting code exactly as shown.
     */
    private void initGUI() {
        add(titLabel, BorderLayout.PAGE_START);

        // center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);

        add(centerPanel, BorderLayout.CENTER);

        // maze panel
        newMaze();
        centerPanel.add(mazePanel);

        // button panel

        // listeners
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                moveBall(keyCode);
            }
        });
    }

    /*
     * 1. Add a private method called newMaze(). It should take
     * no parameters and return nothing.
     * 2. Set the layout for mazePanel to a new GridLayout, rows
     * rows and cols columns. (Hint: use JPane1's setLayout()
     * method and the GridLayout constructor that takes two
     * integers.)
     * 3. Set cell to be a new Cell two-dimensional array, with rows
     * rows and cols columns.
     * 4. Use a double for loop to go through each element in cell,
     * using integers r and c as the iteration variables for the
     * for loops:
     * a. Create a new cell in cell in at r and c. (Hint: use r and
     * c as parameter values for the Cell constructor.)
     * b. Add the cell in cell at r and c to mazePanel. (Hint: use
     * JPanel's add() method.)
     * 
     * 1. Call generateMaze() from the end of newMaze().
     * 
     * When a new maze is created, reset the current and end row
     * and column variables to their initial values.
     * 1. Add code to the end of newMaze() to set row and col to O.
     * 2. Set endRow and endCol to one less than the values of rows
     * and cots, respectively.
     * 3. Set the cell at row and col to the current cell. (Hint: use
     * Cell's setCurrent() method.)
     * 4. Set the cell at endRow and endCol to be the end cell. (Hint:
     * use Cell's setEnd() method.)
     */
    public void newMaze() {
        mazePanel.setLayout(new GridLayout(rows, cols));
        cell = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cell[r][c] = new Cell(r, c);
                mazePanel.add(cell[r][c]);
            }
        }
        generateMaze();

        row = 0;
        col = 0;
        endRow = rows - 1;
        endCol = cols - 1;
        cell[row][col].setCurrent(true);
        cell[endRow][endCol].setEnd(true);
    }

    /*
     * Try It - Remove Some Walls to Form a Maze
     * MazeGenerator needs a way to check whether a cell is
     * available to be added to the maze, in other words, if the cell
     * still has all four walls. However, MazeGenerator must first
     * check whether the given row and column are in the maze.
     * 1. Go back to MazeGenerator.java.
     * 2. Create a private method called isAvailable(). It should
     * take two parameters, integers called r and c, and return a
     * boolean.
     * 3. Create a boolean called available, initialized tofalse.
     * 4. If r and c contain valid row and column values, and if cell
     * at r and c has all four walls, set available to true. (Hint:
     * r and c must be greater than or equal to O and less than
     * the number of rows and cols, respectively. Hint: use Cell's
     * hasAllWalls() method.)
     * 5. Return available.
     */
    public boolean isAvailable(int r, int c) {
        boolean available = false;
        if (r >= 0 && r < rows && c >= 0 && c < cols && cell[r][c].hasAllWalls()) {
            available = true;
        }
        return available;
    }

    /*
     * How do you generate a maze with only one way to reach any
     * cell and with no unreachable cells?
     * • Start at a random cell.
     * • While not all cells have yet been visited,
     * - Find all neighbors with all walls intact.
     * - If one or more was found:
     * ~ If more than one was found, add that cell to the
     * list to try later.
     * ~ Pick a neighbor and remove the adjacent wall.
     * ~ Go to the neighbor and increment the number
     * visited.
     * • If none was found, go to one of the cells saved in the
     * list to try later.
     */
    public void generateMaze() {
        ArrayList<Cell> tryLaterCell = new ArrayList<>();
        int totalCells = rows * cols;
        int visitesCells = 1;

        // start at a random cell
        Random rand = new Random();
        int r = rand.nextInt(rows);
        int c = rand.nextInt(cols);

        // while not all cells have yet been visited
        while (visitesCells < totalCells) {

            // find all neighbors with all walls intact
            ArrayList<Cell> neighbors = new ArrayList<>();
            if (isAvailable(r - 1, c)) {
                neighbors.add(cell[r - 1][c]);
            }
            if (isAvailable(r + 1, c)) {
                neighbors.add(cell[r + 1][c]);
            }
            if (isAvailable(r, c - 1)) {
                neighbors.add(cell[r][c - 1]);
            }
            if (isAvailable(r, c + 1)) {
                neighbors.add(cell[r][c + 1]);
            }

            // if one or more found
            if (neighbors.size() > 0) {
                // if more than one found, add this cell to the list to try again
                if (neighbors.size() > 1) {
                    tryLaterCell.add(cell[r][c]);
                }

                // pick a neighbor and remove the wall
                int pick = rand.nextInt(neighbors.size());
                Cell neighbor = neighbors.get(pick);
                cell[r][c].openTo(neighbor);

                // go to the neighbor and increment the number visited
                r = neighbor.getRow();
                c = neighbor.getCol();
                visitesCells++;
            } else {
                // if none was found, go to one of the cells that was saved to try later
                Cell nextCell = tryLaterCell.remove(0);
                r = nextCell.getRow();
                c = nextCell.getCol();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MazeGenerator();
            }
        });

    }

}