import java.awt.Graphics;
import java.awt.Color;
/**
 * Class structure for low speed, low health YellowEnemy objects
 *
 * @author Peyton Dent
 */
public class YellowEnemy extends Enemy {
    private Color color;
    /**
     * Constructor for YellowEnemy based on superclass constructor.
     *
     * @param inhealth Health for YellowEnemy to start with.
     */
    public YellowEnemy(int inhealth) {
        super(inhealth);
        color = new Color(255, 255, 0);
    }
    /**
     * Constructor for YellowEnemy based on default health value.
     */
    public YellowEnemy() {
        super(8);
        color = new Color(255, 255, 0);
    }
    /**
     * Draw method for YellowEnemy
     *
     * @param g graphics object to draw onto
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
    }
    /**
     * Gets the score value for the YellowEnemy, as well as the
     * factor for the money value.
     */
    public int getValue() {
        return 4;
    }
}
