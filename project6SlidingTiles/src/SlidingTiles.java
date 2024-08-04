
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
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private static final String FILENAME = "project6SlidingTiles\\slidingTilesImage.jpg";
    private int tileSize = 50;
    private int gridSize = 4;
    private BufferedImage image = null;

    /*
     * Next, add the tile buttons.
     * 1. Add a private TileButton two-dimensional array instance
     * variable called tile, with gridSize rows and gridSize columns.
     * 2. Add a private JPanel instance variable called centerPanel,
     * initialized by calling JPanel's constructor.
     */
    private TileButton[][] tile = new TileButton[gridSize][gridSize];
    private JPanel centerPanel = new JPanel();

    /*
     * Divide the image into gridSize rows and gridSize columns of
     * subimages, and put each subimage in a tile button in the center
     * panel.
     * 
     * 1. Create a new private method called dividelmage(). It should take
     * no parameters and return nothing.
     * 
     * 2. Set the layout for centerPanel to a new GridLayout, with gridSize
     * rows and gridSize columns. (Hint: use JPanel's setLayout()
     * method.)
     * 
     * 3. Add centerPanel to the center of the window. (Hint: use JPanel's
     * add() method and BorderLayout's CENTER variable.)
     * 
     * 4. Create an integer called imageld, initialized to O.
     * 
     * 5. Use a double for loop to go through each tile button in tile, using
     * integers row and col as iteration variables for the for loops:
     * a. Create an integer called x, initialized to the number of pixels
     * to the left edge of the piece of the image needed for the tile
     * button at row and col. (Hint: the x values needed will be
     * O, 50, 100, and 150. How would you calculate those values
     * from col and tileSize?)
     * b. Create an integer called y, initialized to the number of pixels
     * to the top edge of the piece of the image needed for the tile
     * button at row and col. (Hint: the y values needed will be
     * O, 50, 100, and 150. How would you calculate those values
     * from row and tileSize?)
     * c. Create a Bufferedlmage object called subimage, initialized
     * by getting a subimage of image. (Hint: use Bufferedlmage's
     * getSubimage() method, passing in the x and y coordinates,
     * and width and height, of each piece of the image to retrieve.)
     * d. Create a new Imagelcon object called imagelcon by calling
     * the Imagelcon constructor with subimage.
     * e. Initialize the tile at row and col to a new TileButton, passing
     * in all the required values.
     * f. Add the tile at row and col to the centerPanel.
     * g. Increment imageld.
     */
    private void dividelmage() {
        centerPanel.setLayout(new GridLayout(gridSize, gridSize));
        add(centerPanel, BorderLayout.CENTER);

        int imageId = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = col*tileSize;
                int y = row*tileSize;
                BufferedImage subImage = image.getSubimage(x, y, tileSize, tileSize);
                ImageIcon imagelcon = new ImageIcon(subImage);
                tile[row][col] = new TileButton(imagelcon, imageId, row, col);
                centerPanel.add(tile[row][col]);
                imageId++;

            }
        }
    }

    /*
     * Create a new private method called initGUI(). It should take no
     * parameters and return nothing. In initGUI(), add a TitleLabel,
     * with text "Sliding Tiles", to the top of the window.
     * 
     * The initGUI() method is going to get quite large. Dividing large
     * blocks of code into smaller sections, and labeling each section with
     * a comment, makes the code easier to write now and easier to
     * understand later. initGUI() could be divided into two sections, one for
     * the main panel and the other for the button panel.
     * 1. In initGUI(), add comments to label sections for the main panel
     * and the button panel.
     * 
     * SlidingTiles will need to put images on the tile buttons in the
     * constructor and again later in the program, so put that code in a
     * separate method.
     * 1. In the main panel section, call dividelmage().
     */
    private void initGUI() {
        JLabel titleLabel = new JLabel("Sliding Tiles");
        add(titleLabel, BorderLayout.PAGE_START);

        // main panel
        dividelmage();

        // button panel
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
        } catch (IOException e) {
            String message = "The image file " + FILENAME + " could not be opened";
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
