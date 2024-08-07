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

    /*
     * To be able to draw on a panel, you must provide a method
     * called paintComponent(), with one parameter, a Graphics
     * object, and returns nothing.
     *
     * Graphics has many methods that can set colors, draw shapes,
     * and much more. For example, to draw a line from position O,
     * 5 to position 10, 20 on a Graphics object called g, use:
     * g.drawLine(O, 5, 10, 20);
     * 
     * 1. Add a new public method to Cell called
     * paintComponent(). paintComponent() should have one
     * parameter, a Graphics object called g, and return
     * nothing.
     * 
     * 2. In paintComponent(), draw a line ong from coordinates
     * O and O to coordinates SIZE and SIZE. (Hint: use
     * Graphics' drawLine() method.)
     * 
     * Try It - Draw a Rectangle on a Panel
     * 
     * Drawing a rectangle is similar to drawing a line: use
     * drawRect() with the location of the top left corner of the
     * rectangle and the width and height of the rectangle. To draw
     * a rectangle at O, 5, 10 pixels wide, and 20 pixels tall, use:
     * g.drawRect(O, 5, 10, 20)
     * 
     * In what color would the rectangle be drawn? The default is
     * black. But ifyou want to use a different color, first switch the
     * color with Graphics' setColor() method.
     * 
     * You can also draw a filled rectangle using a method similar to
     * drawRect():
     *
     * g.fi11Rect(O, 5, 10, 20)
     * 
     * Try out these new methods by drawing a solid white square
     * that's the same size as Cell and outlined in black.
     * 
     * 1. Remove the code in paintComponent().
     * 
     * 2. Set the color for g to white. (Hint: use Graphics' setColor()
     * method.)
     * 
     * 3. Draw a filled rectangle on g that fills the entire cell.
     * (Hint: use Graphics' fillRect() method to fill a rectangle
     * starting at O, O, and SIZE wide and SIZE tall.)
     * 
     * 4. Set the color for g to black. (Hint: use Graphics' setColor()
     * method.)
     * 
     * Try It - Draw a Circle
     * Another shape you'll need to be able to draw for this game
     * is a circle. Before drawing a circle, imagine a rectangle that
     * surrounds your circle. To draw the circle, use drawOvaI()
     * with the position, width and height of that rectangle. For
     * example, to draw an oval that would fit inside a rectangle at
     * O, 10, width 30, and height 15, use:
     * 
     * g.drawOval(O, 10, 30, 15);
     * 
     * Like Java provides both drawRect() andfillRect() methods, so
     * too does it provide both drawOval() andfillOval() methods,
     * with the latter drawing a filled-in circle or oval.
     * 
     * Add code to paintComponent() to draw a smaller green circle
     * inside the square.
     * 
     * 1. Set the color for g to green. (Hint: use Graphics' setColor()
     * method.)
     * 
     * 2. Draw a filled circle on g centered in the cell, 6 pixels
     * smaller than the cell. (Hint: use Graphics' fillOval()
     * method. Hint: if the circle is 6 pixels smaller than the
     * square, where would you have to start drawing the
     * circle so the circle appears centered in the square? Hint:
     * Calculate this number from SIZE.)
     */

    @Override
    public void paintComponent(Graphics g) {
        // g.drawLine(0, 0, SIZE, SIZE);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SIZE, SIZE);
        g.setColor(Color.GREEN);
        g.fillOval(3, 3, SIZE - 6, SIZE - 6);

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
}
