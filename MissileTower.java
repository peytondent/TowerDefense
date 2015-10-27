import java.awt.Color;
/**
 * MissileTower class, which has slow attack rate, increasing explosion radius,
 * and increasing damage.  Creates explosions around target enemy to attack.
 *
 * @author Peyton Dent
 */
public class MissileTower extends Tower {
    private int explosionradius;
    /**
     * Constructor for MissileTower object
     *
     * @param x x value of the tower
     * @param y y value of the tower
     */
    public MissileTower(int x, int y) {
        super(x, y);
        setRange(150);
        setPrice(40);
        setDamage(2);
        setAttackDelay(150);
        setColor(new Color(139, 0, 204));
        setRangeColor(new Color(139, 0, 204, 63));
        explosionradius = 30;
    }
    /**
     * Creates an explosion to damage enemy and surrounding enemies
     *
     * @param e target enemy
     */
    public Explosion explode(Enemy e) {
        return (new Explosion(explosionradius, (e.getx()), (e.gety()), damage));
    }
    /**
     * Upgrades the tower.
     */
    public void upgrade() {
        if (upgrade < 3) {
            explosionradius = explosionradius + 30;
            setDamage(damage + 2);
            upgrade++;
        }
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
}
