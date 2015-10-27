import java.awt.Graphics;
import java.awt.Color;
/**
 * Class structure for high health, high speed BlackEnemy objects
 *
 * @author Peyton Dent
 */
public class BlackEnemy extends Enemy {
    private Color color;
    /**
     * Constructor for BlackEnemy based on superclass constructor.
     *
     * @param inhealth Health for BlackEnemy to start with.
     */
    public BlackEnemy(int inhealth) {
        super(inhealth);
        color = new Color(0, 0, 0);
    }
    /**
     * Constructor for BlackEnemy based on default health value.
     */
    public BlackEnemy() {
        super(24);
        color = new Color(0, 0, 0);
    }
    /**
     * Updates values of BlackEnemy for each timer step.
     */
    @Override public void step() {
    location++;
    location++;
    location++;
    x = Calculator.getx(location);
    y = Calculator.gety(location);
    }
    /**
     * Draw method for BlackEnemy
     *
     * @param g graphics object to draw onto
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
    }
    /**
     * Gets the score value for the BlackEnemy, as well as the factor for the
     * money value.
     */
    public int getValue() {
        return 12;
    }
}
