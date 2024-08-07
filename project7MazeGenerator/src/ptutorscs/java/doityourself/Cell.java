package ptutorscs.java.doityourself;

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
     */

    @Override
    public void paintComponent(Graphics g) {
        g.drawLine(0, 0, SIZE, SIZE);

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
    public Dimension getPreferredSize(){
        Dimension size = new Dimension(SIZE,SIZE);
        return size;
    }
}
