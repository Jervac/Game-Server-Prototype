package Visionire.path;

import Visionire.Framework.Objects.PathObject;
import Visionire.Framework.Objects.WaypointObject;
import Visionire.Utility.Log;
import Visionire.Utility.Time;


/**
 *
 * @author nickbabenko
 */
public class PathCalculator {
    public static PathObject calculate(WaypointObject start_location, WaypointObject end_location) {
        PathObject path = new PathObject();

        double point_x = end_location.x() - start_location.x();
        double point_y = end_location.y() - start_location.y();

        double angle = Math.atan2(point_x, point_y);
        double dist = Math.sqrt(Math.pow(point_x, 2) + Math.pow(point_y, 2));
        int length = (int) (dist / Visionire.path.PathProperties.SPEED);
        long now = Time.now();

        Log.out(Log.DEBUG, "current time: " + now + " - " + length, PathCalculator.class);

        path.add(start_location.worldTime(now).orientation(angle));
        path.add(end_location.worldTime((now + length)).orientation(angle));
        return path;
    }
}
