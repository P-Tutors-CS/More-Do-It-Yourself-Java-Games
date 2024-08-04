
/* Project 6 - Sliding Tiles
Next youlll create the Sliding Tiles game. A photo will be divided
into 16 tiles in 4 rows and 4 columns, with the bottom right corner
missing. The tiles will then be scrambled and you will be challenged
to slide the tiles back into their original positions. Once all the tiles
are in the correct order, the missing tile from the bottom right corner
will also be displayed.
To slide a tile, you must click a tile next to the empty space. Doing so
will slide the clicked tile into that empty space. */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class SlidingTiles extends JFrame {
    private static final long serialVersionUID = 1L;

    /*
     * Initialize the variables needed for reading the image and breaking it
     * into correctly sized subimages.
     * 
     * 1. Go back to SlidingTiles.java.
     * 
     * 2. Add a private static final string instance variable called
     * FILENAME, set to "slidingTilesImage.jpg".
     * 
     * 3. Add two private integer instance variables: one called tileSize,
     * initialized to 50, and the other called gridSize, initialized to 4.
     * 
     * 4. Add a private Bufferedlmage instance variable called image,
     * initialized to null.
     */
    private static final String FILENAME = "slidingTilesImage.jpg";
    private int tileSize = 50;
    private int gridSize = 4;
    private BufferedImage image = null;

    /*
     * Create a new private method called initGUI(). It should take no
     * parameters and return nothing. In initGUI(), add a TitleLabel,
     * with text "Sliding Tiles", to the top of the window.
     */
    private void initGUI() {
        JLabel titleLabel = new JLabel("Sliding Tiles");
        add(titleLabel, BorderLayout.PAGE_START);
    }

    /*
     * Add a SlidingTiles constructor with no parameters. In the
     * constructor:
     * a. call initGU1().
     * b. Set the window's title to "Sliding Tiles", disable resizing,
     * pack the window, center the window, make the window
     * visible, and set the program to exit when the window is
     * closed.
     * 
     * Now, read the image.
     * 1. At the beginning of the constructor, give image a value by
     * calling ImagelO's read() method with a new file created using
     * FILENAME.
     * 
     * 2. Place all the constructor code in a try block, because reading the
     * image file could throw an exception.
     * 
     * 3. Add a catch block after the try block to catch an IOException. In
     * the catch block:
     * a. Create a string called message, stating the image file couldn't
     * be opened.
     * b. Display message in a message dialog. (Hint: use
     * JOptionPane's. showMessageDialog() method.)
     * 
     */
    public SlidingTiles() {
        try {
            image = ImageIO.read(new File(FILENAME));

            /*
             * Add code to the constructor of SlidingTiles before it
             * calls initGUI() to set the TileButton's size to tileSize and
             * set the maximum number of tiles. (Hint: use TileButton's
             * setTileSizeAndMaxTiles() method. It is a static method, so type
             * the class name in front of the method. Hint: How many squares
             * are there in a grid of gridSize rows and gridSize columns?)
             */
            TileButton.setTileSizeAndMaxTiles(tileSize, gridSize * gridSize);

            initGUI();

            setTitle("Sliding Tiles");
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } catch (Exception e) {
            String message = "The image file " + FILENAME + "could not be opened";
            JOptionPane.showMessageDialog(this, message);
        }
    }

    public static void main(String[] args) throws Exception {
        // Add code to main() to use the cross platform look and feel and to
        // create a new SlidingTiles object on the event queue.
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            // TODO: handle exception
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SlidingTiles();
            }
        });

    }

}
