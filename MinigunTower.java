import java.awt.Color;
/**
 * MinigunTower class, which has short range, high firing rate, and upgradeable
 * damage
 *
 * @author Peyton Dent
 */
public class MinigunTower extends Tower {
    /**
     * Constructor for MinigunTower object
     *
     * @param x x value of tower
     * @param y y value of tower
     */
    public MinigunTower(int x, int y) {
        super(x, y);
        setRange(75);
        setPrice(30);
        setDamage(1);
        setAttackDelay(20);
        setColor(new Color(0, 0, 255));
        setRangeColor(new Color(0, 0, 255, 63));
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
            setDamage(damage * 2);
            upgrade++;
        }
    }
}
