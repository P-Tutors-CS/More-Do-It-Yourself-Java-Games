
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
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    // private static final String FILENAME =
    // "project6SlidingTiles\\slidingTilesImage.jpg";
    private static final String FILENAME = "/images/slidingTilesImage.jpg";

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

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
     * Try It - Fill In the Missing Tile When the Puzzle is Solved
     * When the puzzle is solved, SlidingTiles will need to fill in the missing
     * portion of the image. How will the SlidingTiles check whether the
     * puzzle is solved? By checking whether all the image IDs are in order.
     * 
     * 1. Add a private method called imagesInOrder(). It should take no
     * parameters and return a boolean.
     * 
     * 2. Create an integer called id, initialized to O.
     * 
     * 3. Create a boolean called inOrder, initialized to true.
     * 
     * 4. Potentially loop through all the rows and columns of tile,
     * continuing while inOrder is true. (Hint: use integers row and col
     * for the iteration variables. Hint: in the for loop for row, continue
     * while row is less than gridSize and inOrder is true. Write similar
     * code in the for loop for col.)
     * 
     * a. Create an integer called currentld, initialized to the imageld
     * of the tile at row and col. (Hint: use TileButton's getlmageld()
     * method.)
     * 
     * b. If currentld is not equal to id, set inOrder to false.
     * 
     * c. Increment id.
     * 
     * 5. Return inOrder.
     */
    private boolean imagesInOrder() {
        int id = 0;
        boolean inOrder = true;

        for (int row = 0; row < gridSize; row++) {

            for (int col = 0; col < gridSize; col++) {
                int currentid = tile[row][col].getImageId();
                if (currentid != id) {
                    inOrder = false;
                }
                id++;
            }
        }
        return inOrder;
    }

    /*
     * If a tile button is clicked that is next to, but not diagonal to, a tile
     * button with no image, SlidingTiles will need to swap images between
     * the clicked button and the button with no image.
     * 
     * 1. Create a private method called clickedTile(). It should take one
     * parameter, a TileButton called clickedTile, and return nothing.
     * 
     * 2. Create two integers called row and col, initialized by getting the
     * row and column values of clickedTile, respectively. (Hint: use
     * TileButton's getRow() and getCol() methods.)
     * 
     * 3. If row is not the first row and the tile above the clicked button has
     * no image, swap clickedTile with that tile. (Hint: depending on the
     * value of row, how do you check whether a row exists above the
     * clicked tile button? Hint: use TileButton's hasNoImage() method.
     * Hint: use TileButton's swap() method.)
     * 
     * 4. Otherwise, if row is not the last row and the tile below the clicked
     * button has no image, swap clickedTiIe with that tile. (Hint: use
     * TileButton's hasNoImage() method. Hint: use TileButton's swap()
     * method.)
     * 
     * 5. Otherwise, if col is not the first column and the tile to the
     * left of the clicked button has no image, swap clickedTile with
     * that tile. (Hint: use TileButton's hasNoImage() method. Hint: use
     * TileButton's swap() method.)
     * 
     * 6. Otherwise, if col is not the last column and the tile to the
     * right of the clicked button has no image, swap clickedTile with
     * that tile. (Hint: use TileButton's hasNoImage() method. Hint: use
     * TileButton's swap() method.)
     * 
     * SlidingTiles will need to check whether the images are in order every
     * time a tile button is clicked.
     * 
     * 1. Add code to the end of clickedTile() to check whether the
     * images are in order. If the images are in order: (Hint: use
     * imagesInOrder().)
     * 
     * a. Show the image for the tile in the bottom right corner.
     * (Hint: use gridSize to calculate the row and column of the
     * bottom right tile button. Hint: use TileButton's showlmage()
     * method.)
     */
    private void clickedTile(TileButton clickedTile) {
        int row = clickedTile.getRow();
        int col = clickedTile.getCol();
        if (row > 0 && tile[row - 1][col].hasNoImage()) {
            clickedTile.swap(tile[row - 1][col]);
        } else if (row < gridSize - 1 && tile[row + 1][col].hasNoImage()) {
            clickedTile.swap(tile[row + 1][col]);
        } else if (col > 0 && tile[row][col - 1].hasNoImage()) {
            clickedTile.swap(tile[row][col - 1]);
        } else if (col < gridSize - 1 && tile[row][col + 1].hasNoImage()) {
            clickedTile.swap(tile[row][col + 1]);
        }

        if (imagesInOrder()) {
            tile[gridSize - 1][gridSize - 1].showImage();
        }
    }

    /*
     * SlidingTiles will scramble the images by repeatedly swapping images
     * with a random tile button next to the tile button with no image.
     * 
     * 1. Add a new private method called scramble(). It should take no
     * parameters and return nothing.
     * 
     * 2. Add integers called openRow and openCol, set to the bottom
     * row and right column, because the game always starts with
     * the bottom right tile button having no image. (Hint: calculate
     * openRow and openCol using gridSize.)
     * 
     * 3. Create a Random object called rand, initialized by calling
     * Random's constructor.
     * 
     * 4. Create a loop that will repeat many times. (Hint: a suggested
     * value is 25 times gridSize.) In the loop:
     * 
     * a. Create an integer called direction, initialized by picking a
     * random number from O to 3. (Hint: use gridSize.)
     * 
     * b. Use a switch block to run different code, based on direction.
     * (Hint: use the UP, DOWN, LEFT, and RIGHT in the case
     * statements.)
     * 
     * c. To move up, if the open row is not the first row:
     * 
     * a. Swap the tile at the openRow and openCoI with the
     * tile in the previous row. (Hint: use TileButtonis swap()
     * method.)
     * 
     * b. Update openRow to the previous row.
     * 
     * d. Repeat similar code for each direction.
     */
    private void scramble() {
        int openRow = gridSize - 1;
        int openCol = gridSize - 1;

        Random rand = new Random();

        for (int i = 0; i < 25 * gridSize; i++) {
            int direction = rand.nextInt(gridSize);
            switch (direction) {
                case UP:
                    if (openRow > 0) {
                        tile[openRow][openCol].swap(tile[openRow - 1][openCol]);
                        openRow--;
                    }
                    break;
                case DOWN:
                    if (openRow < gridSize - 1) {
                        tile[openRow][openCol].swap(tile[openRow + 1][openCol]);
                        openRow++;
                    }
                    break;
                case LEFT:
                    if (openCol > 0) {
                        tile[openRow][openCol].swap(tile[openRow][openCol - 1]);
                        openCol--;
                    }
                    break;
                case RIGHT:
                    if (openCol < gridSize - 1) {
                        tile[openRow][openCol].swap(tile[openRow][openCol + 1]);
                        openCol++;
                    }

            }
        }
    }

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
     * 
     * 
     * Add an action listener to process tile button clicks:
     * 
     * 1. Go back to SlidingTiles.java.
     * 
     * 2. In dividelmage(), as you create each tile, add an action listener
     * to the tile. (Hint: use TileButton's addActionListener() method,
     * with a new ActionListener object as the parameter value. In the
     * ActionListener, add a public method called actionPerformed().
     * actionPerformed() should have one parameter, an ActionEvent
     * called e, and return nothing.
     * 
     * 3. In actionPerformed(), create a TileButton object called button,
     * initialized by getting the source of e. (Hint: use ActionEvent's
     * getSource() method. Hint: use Quick Fix to cast the returned
     * value to a TileButton.)
     * 4. Call clickedTile(), passing it button.
     * 
     * Call scramble() at the end of dividelmage().
     */
    private void dividelmage() {

        centerPanel.setLayout(new GridLayout(gridSize, gridSize));
        add(centerPanel, BorderLayout.CENTER);
        /*
         * Normally you add all the components to the window, pack the
         * window, then display the window. If you add or remove components
         * later, the layout may need to be recalculated and components
         * redisplayed. To do that, revalidate the container that holds the
         * components.
         * 
         * Try It - Remove Tiles before Adding New Ones
         * Currently dividelmage() adds tile buttons to the center panel. But
         * what if dividelmage() gets called a second time? Adding tile buttons
         * again will result in too many buttons!
         * 
         * Remove tile buttons in dividelmage() before adding new ones. After
         * adding or removing components in a panel, SlidingTiles will need to
         * revalidate the panel.
         * 1. Remove all the components from centerPanel. (Hint: use JPane1's
         * removeAll() method.)
         * 2. Revalidate centerPane1. (Hint: use JPane1's revalidate() method.)
         */
        centerPanel.removeAll();

        int imageId = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                BufferedImage subImage = image.getSubimage(x, y, tileSize, tileSize);
                ImageIcon imagelcon = new ImageIcon(subImage);
                tile[row][col] = new TileButton(imagelcon, imageId, row, col);
                tile[row][col].addActionListener(new ActionListener() {
                    // @Override
                    public void actionPerformed(ActionEvent e) {
                        TileButton button = (TileButton) e.getSource();
                        clickedTile(button);
                    }
                });
                centerPanel.add(tile[row][col]);
                imageId++;

            }
        }
        centerPanel.revalidate();
        scramble();
    }

    /*
     * The scramble() method expects the tile button with the missing
     * image to be in the bottom right corner. So SlidingTiles will need to
     * start a new game with all the tile button images in correct order.
     * 
     * 1. Add a private method called newGame(). It should take no
     * parameters and return nothing.
     * 2. Create an integer called imageld, initialized to O.
     * 3. Use a double for loop to go through each tile button in tile, using
     * integers row and col as the iteration variables for the for loops:
     * a. Create an integer called x, initialized to the number of pixels
     * to the left edge of the piece of the image needed for the tile
     * button at row and col. (Hint: the x values needed will be
     * O, 50, 100, and 150. How would you calculate those values
     * using col and tileSize?)
     * b. Create an integer called y, initialized to the number of pixels
     * to the top edge of the piece of the image needed for the tile
     * button at row and col. (Hint: the y values needed will be
     * O, 50, 100, and 150. How would you calculate those values
     * using row and tileSize?)
     * c. Create a Bufferedlmage object called subimage, initialized
     * by getting a subimage of image. (Hint: use Bufferedlmage's
     * getSubimage() method, passing in the x and y coordinates
     * and width and height, of each piece of the image to retrieve.)
     * d. Create a new Imagelcon object called imagelcon by calling
     * the Imagelcon constructor with subimage.
     * e. Set the image of the tile at row and col to be imagelcon with
     * imageld for its image ID.
     * f. Increment imageld.
     * 4. After the loop, scramble the image. (Hint: use scramble().)
     */
    public void newGame() {
        int imageId = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                BufferedImage subImage = image.getSubimage(x, y, tileSize, tileSize);
                ImageIcon imageIcon = new ImageIcon(subImage);
                tile[row][col].setImage(imageIcon, imageId);
                imageId++;
            }
        }
        scramble();
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
     * 
     * 
     * Next, give the user a way to start a new game and scramble the puzzle
     * again.
     * 
     * 1. In the button panel section in initGUI(), add a JPaneI object called
     * buttonPaneI to the bottom of the window. (Hint: use JFrame's
     * add() method and BorderLayout's PAGE_END variable.)
     * 
     * 2. Make buttonPanel black. (Hint: use JPanel's setBackground()
     * method.)
     * 
     * 3. Create a JButton called scrambleButton, with text "Scramble".
     * 
     * 4. Add an action listener to scrambleButton to call newGame()
     * when the button is clicked. (Hint: use JButton's
     * addActionListener() method, with a new ActionListener object
     * as the parameter value. In the ActionListener, add a public
     * method called actionPerformed(). actionPerformed() should have
     * one parameter, an ActionEvent e, and return nothing. In
     * actionPerformed(), call newGame().)
     * 
     * 5. Add scrambleButton to buttonPanel.
     */
    private void initGUI() {
        JLabel titleLabel = new JLabel("Sliding Tiles");
        add(titleLabel, BorderLayout.PAGE_START);

        // main panel
        dividelmage();

        // button panel
        JPanel ButtoPanel = new JPanel();
        add(ButtoPanel, BorderLayout.PAGE_END);
        ButtoPanel.setBackground(Color.BLACK);

        JButton scrambleButton = new JButton("Scramble");
        scrambleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        ButtoPanel.add(scrambleButton);
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
            image = ImageIO.read(new File("project6SlidingTiles\\slidingTilesImage.jpg"));
            if (image == null) {
                URL imageUrl = SlidingTiles.class.getResource(FILENAME);
                image = ImageIO.read(imageUrl);
            }

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

    public void loadImage() {
        URL imgURL = getClass().getResource("/resources/your_image.png");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            // Use the icon in your GUI component
        } else {
            System.err.println("Couldn't find file: your_image.png");
        }
    }

}
