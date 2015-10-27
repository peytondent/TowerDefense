import java.awt.Color;
/**
 * RifleTower class, which has long range, moderate damage, and upgradeable
 * attack rate
 *
 * @author Peyton Dent
 */
public class RifleTower extends Tower {
    /**
     * Constructor for RifleTower object
     *
     * @param x x value of tower
     * @param y y value of tower
     */
    public RifleTower(int x, int y) {
        super(x, y);
        setRange(200);
        setPrice(30);
        setDamage(4);
        setAttackDelay(200);
        setColor(new Color(255, 0, 0));
        setRangeColor(new Color(255, 0, 0, 63));
    }
    /**
     * Returns the cost of the next upgrade
     *
     * @return next upgrade cost
     */
    public int getUpgradeCost() {
        if (upgrade == 0) {
            return 30;
        } else if (upgrade == 1) {
            return 60;
        } else if (upgrade == 2) {
            return 90;
        } else {
            return 0;
        }
    }
    /**
     * Upgrades the tower
     */
    public void upgrade() {
        if (upgrade < 3) {
            setAttackDelay(attackdelay / 2);
            upgrade++;
        }
    }
}
