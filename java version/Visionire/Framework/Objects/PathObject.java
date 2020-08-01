package Visionire.Framework.Objects;

import com.google.gson.JsonArray;
import java.util.ArrayList;

/**
 *
 * @author nickbabenko
 */
public class PathObject {
    protected ArrayList<WaypointObject> path;

    public PathObject() {
        path = new ArrayList<WaypointObject>();
    }

    public void add(WaypointObject waypoint) {
        path.add(waypoint);
    }

    public WaypointObject get(int index) {
        return path.get(index);
    }

    public void remove(int index) {
        path.remove(index);
    }

    public WaypointObject getCurrent() {
        WaypointObject current = new WaypointObject();
        if (path.size() > 1) {
            // Calculate the current point between the previous and next waypoints.
            long den = path.get(1).worldTime() - path.get(0).worldTime();
            if (den == 0) {
                current.x(path.get(0).x()).y(path.get(0).y());
            } else {
                long num = current.worldTime() - path.get(0).worldTime();
                current.x(path.get(0).x + ((path.get(1).x - path.get(0).x) * num) / den);
                current.y(path.get(0).y + ((path.get(1).y - path.get(0).y) * num) / den);
            }
        } else if (path.size() == 1) {
            // Return the coordinates of the single waypoint.
            current.x(path.get(0).x()).y(path.get(0).y());
        }
        return current;
    }

    public int length() {
        return path.size();
    }

    /**
     * TRANSFORM METHODS
     */

    public JsonArray toJsonArray() {
        return toJsonArray(-1);
    }

    public JsonArray toJsonArray(int amount) {
        JsonArray path_json = new JsonArray();

        if (amount == -1) {
            for (WaypointObject waypoint : this.path) path_json.add(waypoint.toJsonObject());
        }

        else {
            for (int i = (this.path.size() - amount); i < this.path.size(); i++) {
                path_json.add(this.path.get(i).toJsonObject());
            }
        }
        
        return path_json;

    }
}