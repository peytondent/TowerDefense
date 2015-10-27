import java.awt.Color;
import java.awt.Graphics;
/**
 * Abstract class of all 20px by 20px Tower objects
 *
 * @author Peyton Dent
 */
public abstract class Tower {
    protected int x, y, range, price, damage, attackdelay, attacktimer;
    protected Color color, rangecolor;
    private int attackx, attacky;
    private int shotVisibleDelay;
    protected int upgrade = 0;
    /**
     * Constructor for abstract Tower object
     *
     * @param x x value of the top-left corner of the tower
     * @param y y value of the top=left corner of the tower
     */
    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
        attackx = x + 10;
        attacky = y + 10;
        shotVisibleDelay = 3;
    }
    /**
     * Returns the price of the tower instance
     *
     * @return the price of the tower
     */
    public int getPrice() {
        return price;
    }
    /**
     * Returns the range of the tower
     *
     * @return the range
     */
    public int getRange() {
        return range;
    }
    /**
     * Sets the range to the input value
     *
     * @param inrange new range value
     */
    public void setRange(int inrange) {
        range = inrange;
    }
    /**
     * Sets the price of the tower to the input value
     *
     * @param inprice new price value
     */
    public void setPrice(int inprice) {
        price = inprice;
    }
    /**
     * Sets the damage of the tower to the input value
     *
     * @param indamage new damage value
     */
    public void setDamage(int indamage) {
        damage = indamage;
    }
    /**
     * Sets the attack delay of the tower and resets its attack delay timer
     *
     * @param new delay value
     */
    public void setAttackDelay(int indelay) {
        attackdelay = indelay;
        attacktimer = attackdelay;
    }
    /**
     * Sets the color of the tower
     *
     * @param c new color value
     */
    public void setColor(Color c) {
        color = c;
    }
    /**
     * Sets the color to paint the range of the tower
     *
     * @param c new range color value
     */
    public void setRangeColor(Color c) {
        rangecolor = c;
    }
    /**
     * Returns true if the enemy is in range of the tower and the tower is
     * ready to attack
     *
     * @param e enemy to test attacking
     * @return true if tower can attack enemy
     */
    public boolean canAttack(Enemy e) {
        if (attacktimer == attackdelay) {
            int cx = x;
            int cy = y;
            if (range >= (Calculator.getDistance(cx,
            cy, e.getx(), e.gety()) + 20)) {
                attacktimer = 0;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * Increases the attack delay timer to prepare for the next attack
     */
    public void incrementTimer() {
        if (attacktimer < attackdelay) {
            attacktimer++;
        }
    }
    /**
     * Attacks targeted enemy
     *
     * @param e enemy to attack
     */
    public void attack(Enemy e) {
        e.removeHealth(damage);
        attackx = e.getx();
        attacky = e.gety();
        shotVisibleDelay = 3;
    }
    /**
     * Draws the tower on the input graphics object
     *
     * @param g graphics object to draw on
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
        g.drawLine((x + 10), (y + 10), attackx, attacky);
        if (shotVisibleDelay == 0) {
            attackx = x + 10;
            attacky = y + 10;
        } else {
            shotVisibleDelay--;
        }
        g.setColor(new Color(255, 255, 0));
        if (upgrade > 0) {
            g.fillOval(x, y, 4, 4);
        }
        if (upgrade > 1) {
            g.fillOval((x + 16), y, 4, 4);
        }
        if (upgrade > 2) {
            g.fillOval((x + 8), (y + 16), 4, 4);
        }
    }
    /**
     * Draws the range of the tower on input graphics object
     *
     * @param g graphics object to be drawn on
     */
    public void drawRange(Graphics g) {
        g.setColor(rangecolor);
        g.fillOval((x + 10 - range), (y + 10 - range), 2 * range, 2 * range);
    }
    /**
     * Returns the x value of the top-left corner of the tower
     *
     * @return the x value of the tower
     */
    public int getX() {
        return x;
    }
    /**
     * Returns the y value of the top-left corner of the tower
     *
     * @return the y value of the tower
     */
    public int getY() {
        return y;
    }
    /**
     * Returns true if the tower occupies the input points
     *
     * @param ix
     * @param iy
     * @return true if the tower's x and y equal the input x and y
     */
    public boolean comparePoint(int ix, int iy) {
        return ((ix == x) && (iy == y));
    }
    /**
     * Returns the current attack timer value
     *
     * @return current attack timer
     */
    public int getTimer() {
        return attacktimer;
    }
    public abstract void upgrade();
    public abstract int getUpgradeCost();
}
