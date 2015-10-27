import java.awt.Graphics;
import java.awt.Color;
/**
 * Explosion class, which deals damage to all enemies in a certain area
 *
 * @author Peyton Dent
 */
public class Explosion {
    private int radius, x, y, damage, linger;
    /**
     * Constructor for Explosion object
     *
     * @param inrad radius of the Explosion
     * @param inx x of the center of the explosion
     * @param iny y of the center of the explosion
     * @param indamage damage of the explosion done to enemies
     */
    public Explosion(int inrad, int inx, int iny, int indamage) {
        radius = inrad;
        x = inx;
        y = iny;
        damage = indamage;
        linger = 20;
    }
    /**
     * Draws the explosion on the graphics object; lingers for a limited
     * period of time
     *
     * @param g graphics object to be drawn on
     */
    public void draw(Graphics g) {
        g.setColor(new Color(255, 127, 0, 127));
        g.fillOval((x - radius), (y - radius), (2 * radius) , (2 * radius));
        linger--;
    }
    /**
     * Returns true if the explosion is gone
     *
     * @return true if the explosion is gone
     */
    public boolean checkLinger() {
        return (linger > 0);
    }
    /**
     * Returns true if the explosion will damage input enemy
     *
     * @param e enemy to be tested
     * @return true if the enemy is in range
     */
    public boolean isInRange(Enemy e) {
        return (Calculator.getDistance((x), (y), (e.getx()),
        (e.gety())) <= radius);
    }
    /**
     * Sets the damage of the explosion
     *
     * @param newdmg new damage to be set
     */
    public void setDamage(int newdmg) {
        damage = newdmg;
    }
    /**
     * Damages input enemy by the explosion's damage
     *
     * @param e enemy to be damaged
     */
    public void damageEnemy(Enemy e) {
        e.removeHealth(damage);
    }
}
