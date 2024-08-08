package ptutorscs.java.doityourself;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private int rows = 30;
    private int cols = 30;
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
     * 
     * Try It - Add a "New Maze" Button
     * Another improvement to this game would be to add a button
     * to generate a new maze.
     * 1. In the button panel section of initGUI(), create a new
     * JPane1 object called buttonPanel.
     * 2. Make buttonPanel black. (Hint: use JButton's
     * setBack ound() method.)
     * 3. Add buttonPanel to the bottom of the window. (Hint: use
     * JFrame's add() method and BorderLayout's PAGE_END
     * variable.)
     * 4. Create a new JButton object called newMazeButton, with
     * text "New Maze".
     * 5. Add an action listener to newMazeButton to call
     * newMaze() when the button is clicked. (Hint: use
     * JButton's addActionListener() method, with a new
     * ActionListener object as the parameter value. In
     * the ActionListener, add a public method called
     * actionPerformed(). actionPerformed() should have one
     * parameter, an ActionEvent, and return nothing. In
     * actionPerformed(), call newMaze().)
     * 6. Add newMazeButton to buttonPanel.
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
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);

        JButton newMazeButton = new JButton("New Maze");
        newMazeButton.setFocusable(false);
        newMazeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newMaze();
            }
        });
        buttonPanel.add(newMazeButton);

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
     * 
     * Try It - Remove and Revalidate Components
     * Why doesn't the New Maze button do anything? Because all
     * the old cells are still on the panel and new cells were simply
     * added beyond the size of the window. The old cells must be
     * removed and the panel redisplayed to show the new cells.
     * 1. In newMaze(), remove all the components from
     * mazePanel at the beginning of the method. (Hint: use
     * JPanel's removeAll() method.)
     * 2. At the end of the method, rebuild all the components
     * that are in mazePanel. (Hint: use JPanel's revalidate()
     * method.)
     */
    public void newMaze() {
        mazePanel.removeAll();
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

        mazePanel.revalidate();
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

    /*
     * You should have syntax errors because you haven't written
     * the moveBall() method yet. But before you write the
     * moveBall() method, create another method that moveBall()
     * will use.
     * 1. Add a private method called moveTo(). It should take
     * two parameters, integers called nextRow and nextCol,
     * and return nothing.
     * 2. Set the cell at row and col to no longer be the current cell.
     * (Hint: use Cell's setCurrent() method.)
     * 3. Set row and col to the values of nextRow and nextCol,
     * respectively.
     * 4. Set the cell at row and col to be the current cell.
     * 
     * Next, draw a line showing the path taken through the maze.
     * You'll need to set the direction from which the path left the
     * previous cell and the direction to which it entered the next
     * cell.
     * 1. Update moveTo() to take two more parameters, two
     * integers called firstDirection and secondDirection.
     * 2. Set the path of the first cell to firstDirection. (Hint: use
     * Cell's addPath().)
     * 3. Set the path of the second cell to secondDirection. (Hint:
     * use Cell's addpath().)
     */
    private void moveTo(int nextRow, int nextCol, int firstDirection, int secondDirection) {
        cell[row][col].setCurrent(false);
        cell[row][col].addPath(firstDirection);
        row = nextRow;
        col = nextCol;
        cell[row][col].setCurrent(true);
        cell[row][col].addPath(secondDirection);
    }

    /*
     * Now write the code that will move the ball in the right
     * direction.
     * 1. Add a private method called moveBall(). It should take
     * one parameter, an integer called direction, and return
     * nothing.
     * 2. Use a switch block that tests direction:
     * a. If direction is VK_UP: (Hint: use KeyEvent's VK_UP
     * variable for a case in the switch block.)
     * a. Ifthe cell at row and col doesn't have a top wall:
     * (Hint: use Cell's isWall() method and Cell's TOP
     * variable.)
     * b. Move to the previous row, same column. (Hint:
     * call moveTo() with row- I and col.)
     * b. Write similar code so if direction is VK_DOWN and
     * if this cell doesn't have a bottom wall, move to the
     * next row, same column.
     * c. Write similar code so if direction is VK_LEFT and if
     * this cell doesn't have a left side wall, move to the
     * same row, previous column.
     * d. Write similar code so if direction is VK_RIGHT and if
     * this cell doesn't have a right side wall, move to the
     * same row, next column.
     * 
     * Try It - Move the Ball Further
     * With Each Keystroke
     * One more thing would make movement through the maze
     * a little easier. Every time you press a cursor movement key,
     * continue moving the ball in a straight line until it reaches an
     * opening it could turn into, or until the ball is stopped by a
     * wall.
     * 1. Add code to each case in moveBall(). (Hint: use Cell's
     * isWall() and moveTo() methods and Cell's TOP, BOTTOM,
     * LEFT, and RIGHT variables as needed.)
     * a. After moving up, while the cell at row and col
     * doesn't have a top wall and has both left and right
     * walls, move to the previous row.
     * b. After moving down, while the cell at row and col
     * doesn't have a bottom wall and has both left and
     * right walls, move to the next row.
     * c. After moving left, while the cell at row and col
     * doesn't have a left wall and has both top and bottom
     * walls, move to the previous column.
     * d. After moving right, while the cell at row and col
     * doesn't have a right wall and has both top and
     * bottom walls, move to the next column.
     * 
     * Try It - When the Puzzle is Solved
     * How can you tell if the user solved the puzzle? The puzzle is
     * solved if the ball moved to the cell at end row and end col.
     * 1. At the end of moveBall(), check whether row and col are
     * at endRow and endCol. If they are:
     * a. Create a string called message, congratulating the
     * user on solving the maze.
     * b. Display message in a message dialog. (Hint: use
     * JOptionPane's showMessageDialog() method.)
     */
    private void moveBall(int direction) {
        switch (direction) {
            case KeyEvent.VK_UP:
                // move up if this cell does not have a top wall
                if (!cell[row][col].isWall(Cell.TOP)) {
                    moveTo(row - 1, col, Cell.TOP, Cell.BOTTOM);
                    // move up more if this is a long column
                    while (!cell[row][col].isWall(Cell.TOP)
                            && cell[row][col].isWall(Cell.LEFT)
                            && cell[row][col].isWall(Cell.RIGHT)) {
                        moveTo(row - 1, col, Cell.TOP, Cell.BOTTOM);
                    }
                }

                break;
            case KeyEvent.VK_DOWN:
                // move down if this cell does not have a bottom wall
                if (!cell[row][col].isWall(Cell.BOTTOM)) {
                    moveTo(row + 1, col, Cell.BOTTOM, Cell.TOP);
                    // move down more if this is a long column
                    while (!cell[row][col].isWall(Cell.BOTTOM)
                            && cell[row][col].isWall(Cell.LEFT)
                            && cell[row][col].isWall(Cell.RIGHT)) {
                        moveTo(row + 1, col, Cell.BOTTOM, Cell.TOP);
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                // move left if this cell does not have a left wall
                if (!cell[row][col].isWall(Cell.LEFT)) {
                    moveTo(row, col - 1, Cell.LEFT, Cell.RIGHT);
                    // move left more if this is a long column
                    while (!cell[row][col].isWall(Cell.LEFT)
                            && cell[row][col].isWall(Cell.TOP)
                            && cell[row][col].isWall(Cell.BOTTOM)) {
                        moveTo(row, col - 1, Cell.LEFT, Cell.RIGHT);
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                // move right if this cell does not have a right wall
                if (!cell[row][col].isWall(Cell.RIGHT)) {
                    moveTo(row, col + 1, Cell.RIGHT, Cell.LEFT);
                    // move right more if this is a long column
                    while (!cell[row][col].isWall(Cell.RIGHT)
                            && cell[row][col].isWall(Cell.TOP)
                            && cell[row][col].isWall(Cell.BOTTOM)) {
                        moveTo(row, col + 1, Cell.RIGHT, Cell.LEFT);
                    }
                }
                break;
        }
        // puzzle solved?
        if (row == endRow && col == endCol) {
            String message = "Congratulations! You solved it.";
            JOptionPane.showMessageDialog(this, message);
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