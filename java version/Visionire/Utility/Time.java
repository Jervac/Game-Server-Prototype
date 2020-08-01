package Visionire.Utility;

/**
 *
 * @author nickbabenko
 */
public class Time {

    public static long now() {
        return (long) (System.currentTimeMillis() % (Math.pow(2, 32) - Math.pow(2, 31)));
    }

}
