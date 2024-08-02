
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class LightButton extends JButton {
    private static final long serialVersionUID = 1L;

    private static final int MAXSIZE = 200;
    private int row = 0;
    private int col = 0;

    public LightButton(int row, int col) {
        this.row = row;
        this.col = col;
        setBackground(Color.BLACK);
        Dimension size = new Dimension(MAXSIZE, MAXSIZE);
        setPreferredSize(size);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void turnOn() {
        setBackground(Color.RED);
    }

    public void turnOff() {
        setBackground(Color.BLACK);
    }

    public boolean isLit() {
        return getBackground().equals(Color.RED);
    }

    public void toggle() {
        if (isLit()) {
            turnOff();
        } else {
            turnOn();
        }
    }
}
