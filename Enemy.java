import java.awt.Graphics;
/**
 * Super class for all Enemy objects, which are 20px by 20px
 *
 * @author Peyton Dent
 */
public abstract class Enemy {
    protected int location, health, x, y;
    /**
     * Constructor for Enemy object
     *
     * @param inhealth starting health value
     */
    public Enemy(int inhealth) {
        health = inhealth;
        location = 0;
        x = Calculator.getx(location);
        y = Calculator.gety(location);
    }
    /**
     * Sets health of Enemy object to new value
     *
     * @param inhealth new health value
     */
    public void setHealth(int inhealth) {
        health = inhealth;
    }
    /**
     * Gets X value of the center of the enemy
     */
    public int getx() {
        return x + 10;
    }
    /**
     * Gets Y value of the center of the enemy
     */
    public int gety() {
        return y + 10;
    }
    /**
     * Sets x and y to the new location's value
     *
     * @param inloc location to retrieve x and y from
     */
    public void setLocation(int inloc) {
        location = inloc;
        x = Calculator.getx(location);
        y = Calculator.gety(location);
    }
    /**
     * Updates enemy values each timer step based on default movement speed
     */
    public void step() {
        location++;
        x = Calculator.getx(location);
        y = Calculator.gety(location);
    }
    /**
     * Removes an amount of health from the enemy
     *
     * @param reduction amount of health to be removed
     */
    public void removeHealth(int reduction) {
        health = health - reduction;
    }
    /**
     * Returns true if the enemy has health remaining
     *
     * @return whether or not the enemy is still alive
     */
    public boolean checkHealth() {
        return (health > 0);
    }
    /**
     * Returns true if the enemy is at the end of the path
     *
     * @return whether or not the enemy has completed the path
     */
    public boolean checkWin() {
        return (location > 1940);
    }
    public abstract void draw(Graphics g);
    public abstract int getValue();
}
