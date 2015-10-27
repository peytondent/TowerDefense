import java.awt.Graphics;
//WES WAS HERE
import java.awt.Color;
/**
 * Class structure for medium speed, medium health OrangeEnemy objects.
 *
 * @author Peyton Dent
 */
public class OrangeEnemy extends Enemy {
    private Color color;
    /**
     * Constructor for OrangeEnemy based on superclass constructor.
     *
     * @param inhealth Health for OrangeEnemy to start with.
     */
    public OrangeEnemy(int inhealth) {
        super(inhealth);
        color = new Color(255, 175, 0);
    }
    /**
     * Constructor for OrangeEnemy based on default health value.
     */
    public OrangeEnemy() {
        super(16);
        color = new Color(255, 175, 0);
    }
    /**
     * Updates values of OrangeEnemy for each timer step.
     */
    @ Override public void step() {
        location++;
        location++;
        x = Calculator.getx(location);
        y = Calculator.gety(location);
    }
    /**
     * Draw method for OrangeEnemy
     *
     * @param g graphics object to draw onto
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
    }
    /**
     * Gets the score value for the OrangeEnemy, as well as the factor for the
     * money value.
     */
    public int getValue() {
        return 8;
    }
}
