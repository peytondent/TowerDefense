import java.awt.Color;
/**
 * BombTower class, which has slow attack rate, increasing range, and
 * increasing damage.  Creates explosions around itself to attack.
 *
 * @author Peyton Dent
 */
public class BombTower extends Tower {
    /**
     * Constructor for BombTower object
     *
     * @param x x value of the tower
     * @param y y value of the tower
     */
    public BombTower(int x, int y) {
        super(x, y);
        setRange(50);
        setPrice(40);
        setDamage(4);
        setAttackDelay(100);
        setColor(new Color(255, 255, 255));
        setRangeColor(new Color(255, 255, 255, 63));
    }
    /**
     * Creates an explosion to damage enemy and surrounding enemies
     *
     * @param e target enemy
     */
    public Explosion explode(Enemy e) {
        return (new Explosion(range, (getX() + 10), (getY() + 10), damage));
    }
    /**
     * Returns the cost of the next upgrade
     *
     * @return next upgrade cost
     */
    public int getUpgradeCost() {
        if (upgrade == 0) {
            return 40;
        } else if (upgrade == 1) {
            return 80;
        } else if (upgrade == 2) {
            return 120;
        } else {
            return 0;
        }
    }
    /**
     * Upgrades the tower
     */
    public void upgrade() {
        if (upgrade < 3) {
            setRange(range + 25);
            setDamage(damage + 2);
            upgrade++;
        }
    }
}
