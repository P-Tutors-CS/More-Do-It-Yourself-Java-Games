/* 
SlidingTi1es needs tile buttons that include image and row and
column information. The tile button never actually moves. Ifthe tile
button is clicked, SlidingTiles will need to swap images with the next
tile button. Like the light button in Framed and the terrain button in
Watch Your Step, a tile button behaves much like a JButton, so you
can simply extend the JButton class and add additional features.
Create the TileButton class.
    1. Create a new class called TileButton in the slidingtiles package,
    with superclassJButton and with no stub code.

    2. Use Quick Fix to add a private static final long instance variable
    called serialVersionUID, set to IL.

    3. Add two private static integer instance variables, called tileSize
    and maxTiles, each initialized to O. (Hint: these variables are
    static but not final.)

    4. Add a private Imagelcon instance variable named imagelcon.

    5. Add three private integer instance variables called imageld, row,
    and col, each with an initial value of O. 
*/

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static int tileSize = 0;
    private static int maxTiles = 0;

    private ImageIcon imageIcon;
    private int imageId = 0;
    private int row = 0;
    private int col = 0;

    /*
     * SlidingTi1es will need a way to set the tile button's image. Each image
     * will be paired with an image ld. The image with the largest expected
     * image ld, is from the bottom right corner ofthe photo and will not be
     * displayed on the tile button. That image will be displayed later, when
     * the puzzle is solved.
     * 
     * 1. Add a public method called setlmage(). It should take two
     * parameters, an Imagelcon named imagelcon and an integer
     * named imageld, and return nothing.
     * 
     * 2. Set the instance variable imagelcon to the imagelcon parameter
     * value. (Hint: use this.)
     * 
     * 3. Set the instance variable imageld to the imageld parameter value.
     * (Hint: use this.)
     * 
     * 4. If the imageld is equal to the greatest expected image number, set
     * the tile button's icon to null. (Hint: maxTiles holds the number
     * of tile buttons to expect. If you start numbering tile buttons at
     * O, what would be the number of the last one? Hint: use JButton's
     * setlcon() method.)
     * 5. Otherwise, set the button's icon to imagelcon. (Hint: use
     * JButton's setlcon() method.)
     */
    public void setImage(ImageIcon imageIcon, int imageId) {
        this.imageIcon = imageIcon;
        this.imageId = imageId;
        if (imageId == maxTiles - 1) {
            setIcon(null);
        } else {
            setIcon(imageIcon);
        }
    }

    /*
     * Next create a constructor for TileButton. It will need to assign values
     * to TileButton's instance variables.
     * 1. Add a TileButton constructor with four parameters: an
     * Imagelcon called imagelcon and three integers called imageld,
     * row, and col.
     * 
     * 2. Set the instance variables row and col to the row and col
     * parameter values. (Hint: use this.)
     * 
     * 3. Call setlmage() passing imagelcon and imageld as parameter
     * values.
     * 
     * 4. Set the background of the tile button to white. (Hint: use
     * JButton's setBackground() method.)
     * 
     * 5. Set the border of the tile button to null. (Hint: use JButton's
     * setBorder() method.)
     *
     * 6. Create a new Dimension object called size, tileSize rows and
     * tileSize columns.
     * 
     * 7. Set the preferred size of the tile button to size. (Hint: use
     * JButton's setpreferredSize().)
     * 
     * 8. Turn off the focus mark painted on the button. (Hint: use
     * JButton's setFocusPainted() method.)
     */
    public TileButton(ImageIcon imageIcon, int imageId, int row, int col) {
        this.row = row;
        this.col = col;
        setImage(imageIcon, imageId);

        setBackground(Color.WHITE);
        setBorder(null);
        Dimension size = new Dimension(tileSize, tileSize);
        setPreferredSize(size);
        setFocusPainted(false);
    }

    /*
     * SlidingTiles will need a way to get the values of the private instance
     * variables.
     * 1. Add a private method called getlmage(). It should take no
     * parameters and return an Imagelcon: imagelcon.
     * 
     * 2. Add a public method called getRow(). It should take no
     * parameters and return an integer: row.
     * 
     * 3. Add a public method called getCol(). It should take no parameters
     * and return an integer: col.
     * 
     * 4. Add a public method called getlmageld(). It should take no
     * parameters and return an integer: imageld.
     */
    public ImageIcon getImage() {
        return imageIcon;
    }

    public int getImageId() {
        return imageId;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /*
     * SlidingTiles will allow the program user to change the number of
     * tiles in the puzzle. A method must be provided for SlidingTi1es to set
     * the tile size and the maximum number of tiles. This method must be
     * static so SlidingTiles can call it before any TileButtons are created.
     * 
     * 1. Add a public static method called setTileSizeAndMaxTiles(). It
     * should take two parameters, an integer called size and an integer
     * called max, and return nothing.
     * 
     * 2. Set tileSize to the value of size.
     * 
     * 3. Set maxTiles to the value of max.
     */
    public static void setTileSizeAndMaxTiles(int size, int max) {
        tileSize = size;
        maxTiles = max;
    }

    /*
     * The window should still display the original image, not scrambled,
     * and with the bottom right corner missing, just like before.
     * Try It - Add TileButton Methods for Swapping Images
     * SlidingTiles will need to check whether the tile button has an image.
     * The tile button will have no image if the value of its icon is null.
     * 
     * 1. Add a public method called hasNoImage(). It should take no
     * parameters and return a boolean.
     * 
     * 2. Create a boolean called hasNoImage, initialized tofalse.
     * 
     * 3. If the tile button's icon value is null, set hasNoImage to true.
     * (Hint: use JButton's getlcon() method.)
     * 
     * 4. Return hasNoImage.
     */
    public boolean hasNoImage() {
        boolean hasNoImage = false;
        if (getIcon() == null) {
            hasNoImage = true;
        }
        return hasNoImage;
    }

    /*
     * SlidingTiIes will need to swap images between tile buttons, thus
     * making the window look like the tile was moved.
     * 
     * 1. Add a public method called swap(). It should take one parameter,
     * a TileButton called otherTile and return nothing.
     * 
     * 2. Create an Imagelcon object called otherlmagelcon, initialized
     * by getting the image from otherTile. (Hint: use TileButton's
     * getlmage() method.)
     * 
     * 3. Create an integer called otherlmageld, initialized by getting
     * the imageld from otherTile. (Hint: use TileButton's getlmageld()
     * method.)
     * 
     * 4. Set otherTile to use this tile button's imagelcon and imageld.
     * (Hint: use setlmage() with the instance variables imagelcon and
     * imageld. Hint: use this.)
     * 
     * 5. Set this tile button to use otherlmagelcon and otherlmageld.
     * (Hint: use setlmage().)
     */
    public void swap(TileButton otherTile) {
        ImageIcon otherImageIcon = otherTile.getImage();
        int otherlmageld = otherTile.getImageId();
        otherTile.setImage(imageIcon, imageId);
        setImage(otherImageIcon, otherlmageld);

    }

    /*
     * SlidingTiIes needs a way to show the image after the puzzle is solved.
     * 1. Add a public method called showlmage(). It should take no
     * parameters and return nothing.
     * 2. Set the tile button's icon to imagelcon. (Hint: use JButton's
     * setlcon() method.)
     */
    public void showImage(){
        setIcon(imageIcon);
    }
}
