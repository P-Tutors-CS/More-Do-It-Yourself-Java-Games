package ptutorscs.java.doityourself;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    public MazeGenerator() {
        initGUI();

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initGUI() {
        add(titLabel, BorderLayout.PAGE_START);

        /*
         * Try It - Add a Cell to the Window
         * Next, add a Cell to the window.
         * 1. Go back to MazeGenerator.java.
         * 
         * 2. In initGUI(), create a new Cell object called cell,
         * initialized by calling Cell's constructor.
         * 
         * 3. Add cell to the center of the window. (Hint: JFrame's
         * add() method and BorderLayout's CENTER variable.)
         */

         Cell cell = new Cell();
         add(cell,BorderLayout.CENTER);
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