/**
 * Static method class for calculations needed for Tower Defense game
 *
 * @author Peyton Dent
 */
public class Calculator {
    /**
     * Returns the distance between the first x,y pair and the second x,y pair
     *
     * @param x1 x value of object 1
     * @param y1 y value of object 1
     * @param x2 x value of object 2
     * @param y2 y value of object 2
     * @return distance between pairs one and two
     */
    public static double getDistance(int x1, int y1, int x2, int y2) {
        int x = x1 - x2;
        int y = y1 - y2;
        double rtn = Math.sqrt(x * x + y * y);
        return rtn;
    }
    /**
     * Returns the x corresponding to the path location
     *
     * @param location path location
     * @return x value
     */
    public static int getx(int location) {
        if (location <= 520) {
            return location;
        } else if (location <= 720) {
            return 520;
        } else if (location <= 1200) {
            return (520 - (location - 720));
        } else if (location <= 1400) {
            return 40;
        } else {
            return (location - 1360);
        }
    }
    /**
     * Returns the y corresponding to the path location
     *
     * @param location path location
     * @return y value
     */
    public static int gety(int location) {
        if (location <= 520) {
            return 100;
        } else if (location <= 720) {
            return (location - 420);
        } else if (location <= 1200) {
            return 300;
        } else if (location <= 1400) {
            return (location - 900);
        } else {
            return 500;
        }
    }
}
