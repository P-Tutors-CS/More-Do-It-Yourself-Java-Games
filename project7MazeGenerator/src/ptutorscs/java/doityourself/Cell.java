package ptutorscs.java.doityourself;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
Try It - Create a Cell Class
Instead of a grid of clickable buttons, this game will use
a grid of panels on which MazeGenerator will draw the
necessary graphics.

1. Create a new class called Cell in the mazegenerator
package, with superclassJPanel and with no stub code.

2. Use Quick Fix to add a private static final long instance
variable called serialVersionUID, set to 1 L.  

3. Add a private static final integer instance variable called
SIZE, set to 20.
*/
public class Cell extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int SIZE = 20;

    /*
     * Each cell must have a row and a column, and walls on any
     * number of its four sides.
     * 
     * 1. Add four public static final integer instance variables
     * called TOP, RIGHT, BOTTOM, and LEFT, set to O, 1, 2, and
     * 3, respectively.
     * 2. Add two private integer instance variables called row
     * and col, each initialized to -1.
     * 3. Add a private boolean array instance variable called
     * wall, with four elements, all with value true.
     */
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;

    private int row = -1;
    private int col = -1;

    private boolean[] wall = { true, true, true, true };
    private boolean[] path = { false, false, false, false };

    private boolean current = false;
    private boolean end = false;

    /*
     * Initialize the instance variables when the cell is constructed.
     * 
     * 1. Add a Cell constructor with two parameters, integers
     * called row and col.
     * 
     * 2. Set the instance variables row and col to the row and col
     * parameter values. (Hint: use this.)
     * 
     * The cell will need some public methods so MazeGenerator
     * can access the private instance variables.
     * 
     * 1. Add public methods called getRow() and getCol() that
     * return integers, the values of row and col, respectively.
     * 
     * 2. Add a public method called isWall(). It should take one
     * parameter, an integer called index, and return a boolean,
     * the value of the element in wall at index.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWall(int index) {
        return wall[index];
    }

    /*
     * To be able to draw on a panel, you must provide a method
     * called paintComponent(), with one parameter, a Graphics
     * object, and returns nothing.
     * 
     * Cells are created with all four walls present. Later,
     * MazeGenerator will remove walls as it generates the maze.
     * paintComponent() will be called many times while running
     * the program, including after some walls have been removed.
     * Therefore, paintComponent() will need to draw lines only for
     * the walls of the cell that are still present.
     * 
     * 1. In the draw the walls section of paintComponent(), if the
     * top wall is present, draw a line at the top of the cell.
     * (Hint: Is the TOP element in wall true? Hint: draw a line
     * starting at the top left corner of the cell, O, O. Should you
     * change x or y to go to the right? Draw the line the length
     * of SIZE.)
     * 
     * 2. Ifthe left side wall is present, draw a line at the left edge
     * of the cell. (Hint: is the LEFT element in wall true? Hint:
     * draw a line starting at the top left corner of the cell,
     * 0,0. Should you change x or y to draw a downward line?
     * Draw the line the length of SIZE.)
     * 
     * Draw a green ball ifthis cell is the current cell and a red ball if
     * this cell is the end cell.
     * 1. In the draw the balls section of paintComponent, if
     * current is true:
     * a. Set the color of g to green. (Hint: use Graphics'
     * setColor() method.)
     * b. Draw a filled circle on g centered in the cell, 6 pixels
     * smaller than the cell. (Hint: use Graphics' fillOval()
     * method. Hint: if the circle is 6 pixels smaller than
     * the square, where would you have to start drawing
     * the circle so the circle appears centered in the
     * square?)
     * 2. Otherwise, if end is true:
     * a. Set the color of g to red. (Hint: use Graphics'
     * setColor() method.)
     * b. Draw a filled circle on g centered in the cell, 6 pixels
     * smaller than the cell. (Hint: draw the red ball the
     * same size as the green ball.)
     * 
     * Next, draw the paths that have been taken through this cell.
     * 1. In the draw the path section of paintComponent(), set the
     * color Ofg to green.
     * 2. If the following path is true, draw the corresponding
     * line. (Hint: use Graphics' drawLine() method. Hint:
     * calculate the middle and center of the cell using SIZE.)
     * a. If the TOP element in path is true, draw a line on g
     * from the middle top edge of the cell to the center of
     * the cell.
     * b. If the BOTTOM element in path is true, draw a line
     * on g from the middle bottom edge of the cell to the
     * center of the cell.
     * c. If the LEFT element in path is true, draw a line on g
     * from the middle left edge of the cell to the center of
     * the cell.
     * d. If the RIGHT element in path is true, draw a line on
     * g from the middle right edge of the cell to the center
     * of the cell.
     * 
     */
    @Override
    public void paintComponent(Graphics g) {
        // draw backgorund
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZE, SIZE);
        g.setColor(Color.BLACK);

        // draw the walls

        /* regular maze */
        if (isWall(TOP)) {
            g.drawLine(0, 0, SIZE, 0);
        }
        if (isWall(LEFT)) {
            g.drawLine(0, 0, 0, SIZE);
        }
      
        /* Anti Maze */
        // if (!isWall(TOP)) {
        //     g.drawLine(0, 0, SIZE, 0);
        // }
        // if (!isWall(LEFT)) {
        //     g.drawLine(0, 0, 0, SIZE);
        // }
        // draw the path
        g.setColor(Color.GREEN);
        if(path[TOP]){
            g.drawLine(SIZE/2, 0, SIZE / 2, SIZE / 2);
        }
        if (path[BOTTOM]) {
            g.drawLine(SIZE / 2, SIZE, SIZE / 2, SIZE / 2);
        }
        if (path[LEFT]) {
            g.drawLine(0, SIZE/2, SIZE / 2, SIZE / 2);
        }
        if (path[RIGHT]) {
            g.drawLine(SIZE, SIZE/2, SIZE / 2, SIZE / 2);
        }
        // draw the balls
        if (current) {
            g.setColor(Color.GREEN);
            g.fillOval(3, 3, SIZE - 6, SIZE - 6);
        } else if (end) {
            g.setColor(Color.RED);
            g.fillOval(3, 3, SIZE - 6, SIZE - 6);
        }

    }

    /*
     * Try It - Set Cell's Preferred Size
     * The window didn't show the entire line for the cell because
     * the cell's size hasn't been set yet.
     * 
     * 1. Go back to Cell.java.
     * 
     * 2. Add a public method called getPreferredSize(). It should
     * take no parameters and return a Dimension.
     * 
     * 3. Create a new Dimension object called size, SIZE wide and
     * SIZE high.
     * 
     * 4. Return size.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(SIZE, SIZE);
        return size;
    }

    /*
     * 2. Add a public method called hasAllWalls(). It should take
     * no parameters and return a boolean.
     * 3. Add a boolean called allWalls which is true if every
     * element in wall is true. (Hint: use logical and, &&, to
     * compare the contents of each element.)
     * 4. Return allWalls.
     */
    public boolean hasAllWalls() {
        boolean allWalls = wall[0] && wall[1] && wall[2] && wall[3];
        return allWalls;
    }

    /*
     * And MazeGenerator will need to be able to remove a wall
     * from the cell.
     * 1. Add a public method called removeWall(). It should take
     * one parameter, an integer called w, and return nothing.
     * 2. Set the element at w in wall to false.
     * 3. Repaint the cell. (Hint: call JPanel's repaint() method.)
     */
    public void removeWall(int w) {
        wall[w] = false;
        repaint();
    }

    /*
     * MazeGenerator will need a way to make an opening from one
     * cell to the next. A method will be needed to remove the wall
     * between the cells. Note, this involves removing walls in two
     * cells, because each cell is initially created with a wall on each
     * of its 4 sides..
     * 1. Add a public method called openTo(). It should take
     * one parameter, a Cell called neighbor and should return
     * nothing.
     * 2. If row is less than the neighbor's row, remove the bottom
     * wall of this cell and the top wall of neighbor. (Hint, use
     * getRow() to get the row for neighbor. Use removeWall() to
     * remove a wall of this cell and remove a wall of neighbor.
     * Hint: use TOP, BOTTOM, LEFT, or RIGHT as needed.)
     * 3. Otherwise, if row is greater than the neighbor's row,
     * remove the top wall of this cell and the bottom wall
     * of neighbor. (Hint, use getRow(), removeWall(), and TOP,
     * BOTTOM, LEFT, or RIGHT as needed.)
     * 4. Otherwise, if col is less than the neighbor's column,
     * remove the right wall of this cell and the left wall
     * of neighbor. (Hint, use getCol(), removeWall(), and TOP,
     * BOTTOM, LEFT, or RIGHT as needed.)
     * 5. Otherwise, if col is greater than the neighbor's column,
     * remove the left wall of this cell and the right wall
     * Of neighbor. (Hint, use getCoI(), removeWaII(), and TOP,
     * BOTTOM, LEFT, or RIGHT as needed.)F
     */
    public void openTo(Cell neighbor) {
        if (row < neighbor.getRow()) {
            removeWall(BOTTOM);
            neighbor.removeWall(TOP);
        } else if (row > neighbor.getRow()) {
            removeWall(TOP);
            neighbor.removeWall(BOTTOM);
        } else if (col < neighbor.getCol()) {
            removeWall(RIGHT);
            neighbor.removeWall(LEFT);
        } else if (col > neighbor.getCol()) {
            removeWall(LEFT);
            neighbor.removeWall(RIGHT);
        }
    }

    /*
     * MazeGenerator will need a way to make the cell current.
     * 1. Add a public method called setCurrent(). It should take
     * one parameter, a boolean called current, and return
     * nothing.
     * 2. Set the instance variable current to the current
     * parameter value. (Hint: use this.)
     * 3. Repaint the cell. (Hint: use JPanels repaint() method.)
     */
    public void setCurrent(boolean current) {
        this.current = current;
        repaint();
    }

    /*
     * And MazeGenerator will need a way to make the cell the end
     * cell.
     * 1. Add a public method called setEnd(). It should take one
     * parameter, a boolean called end, and return nothing.
     * 2. Set the instance variable end to the end parameter value.
     * (Hint: use this.)
     * 3. Repaint the cell. (Hint: use JPanels repaint() method.)
     */
    public void setEnd(boolean end) {
        this.end = end;
    }

    /*
     * MazeGenerator will need a way to update the path taken
     * through the cell.
     * 1. Add a public method called addPath(). It should take one
     * parameter, an integer called side, and return nothing.
     * 2. Set the path element at side to true.
     * 3. Repaint the cell. (Hint: use JPanels repaint() method.)
     */
    public void addPath(int side) {
        path[side] = true;
        repaint();
    }
}
