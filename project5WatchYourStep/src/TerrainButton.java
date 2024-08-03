import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 * The Watch Your Step game needs terrain buttons that, when clicked,
 * either display if neighboring terrain buttons hide black holes, or end
 * the game if that terrain button hides a black hole. Like the LightButton,
 * terrain buttons behave much like JButtons, so you can simply extend the
 * JButton class and add additional features.
 */
public class TerrainButton extends JButton {

    // Use Quick Fix to add a private static final long instance variable called
    // serialVersionUID set to IL.
    private static final long serialVersionUID = 1L;
    // Add a private static final integer instance variable called SIZE, set to 50.
    private static final int SIZE = 50;
    // Add three private integer variables called row, col, and nextToHoles, all
    // with initial values of O.
    private int row = 0, col = 0, nextToHoles = 0;
    // Add two private boolean instance variables called hole and revealed, each
    // initialized to false.
    private boolean hole = false, revealed = false;

    // Add a constructor with two integer parameters: row and col.
    // Set instance variables, row and col, to the row and col parameter values.
    // (Hint: use this.)
    // Create a new Dimension object called size, SIZE wide (number of rows) and
    // SIZE high (number of columns).
    // Set the preferred size of the terrain button to size. (Hint: use JButton's
    // setPreferredSize() method.)

    public TerrainButton(int row, int col) {
        this.row = row;
        this.col = col;
        Dimension size = new Dimension(SIZE, SIZE);
        setPreferredSize(size);

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasHole() {
        return hole;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setHole(boolean hasHole) {
        hole = hasHole;

    }

    public void increaseHoleCount() {
        nextToHoles++;
    }

    public boolean isNextToHoles() {
        return nextToHoles > 0;
    }


    /*
     * Add a public method called reveal(). It should take one parameter, a
     * boolean called reveal, and return nothing.
     * Set revealed to the value of reveal.
     * If revealed is true:
        * a. If the terrain button hides a hole, set the background color to
        * black. (Hint: use JButton's setBackground() method.)
        * b. Ifthe terrain button does not hide a hole:
            * a. Set the background color to cyan. (Hint: use JButton's setBackground() method.)
            * b. If holes exist next to the terrain button, set the text
            * nextToHoles. (Hint: use JButton's setText() method. Hint: to
            * convert the integer value in nextToHoles to a string, add the
            * integer to an empty string.)
     * Otherwise:
     * a. Set the background color to null.
     * setBackground() method.)(Hint:use JButton's setBackGround() method.)
     * set the text to an empty string (Hint: use the JButton's setText() method.)
     */
    public void reveal(boolean reveal){
        revealed = reveal;

        if(revealed){
            if (hasHole()){
                setBackground(Color.BLACK);
            }else{
                setBackground(Color.CYAN);
                if(nextToHoles > 0){
                    setText(""+nextToHoles);
                }
            }
        }else{
            setBackground(null);
            setText("");
        }
        // Lesson 5.2 - In Focus Mark
        // One more thing you might want to change in this program is how the program
        // paints a focus mark on the last clicked terrain button. A focus mark is not
        // needed and not desired for this program.
        // Try It - Remove the JButton Focus Mark
        // 1. Go to TerrainButton.java.
        // 2. Add code to reveal() to turn off painting the focus indicator. (Hint: use
        // JButtonIs setFocusPainted() method.)
        setFocusPainted(false);
    }

    // WatchYourStep will need a way to reset TerrainButton variables to their
    // original values, to start a new game.
    // 1. Add a public method called reset(). It should take no parameters and return
    // nothing.
    // 2. Reset hole, revealed, and nextToHoIes to their original values.
    // 3. Remove any text. (Hint: use JButton's setText() method. Hint: to remove
    // text, set the text to an empty string.)
    // 4. Return the background to its default value. (Hint: use JButtonis
    // setBackground() method. Hint: to set to the default color, use null as the
    // parameter value.)
    public void reset(){
        hole =false;
        revealed=false;
        nextToHoles = 0;
        setText("");
        setBackground(null);

    }

}
