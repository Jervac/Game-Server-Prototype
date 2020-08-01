package Visionire.Framework.Objects;

import Visionire.Utility.Time;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


/**
 *
 * @author nickbabenko
 */
public class WaypointObject {

    protected double x = 0.0;
    protected double y = 0.0;
    protected double orientation = 0;
    protected long worldTime = -1;
    protected long speed = 1500;

    public WaypointObject() {
        this(0, 0);
    }

    public WaypointObject(double x, double y) {
        this.x = x;
        this.y = y;
        this.orientation = Math.atan2(y, x);
        this.worldTime = Time.now();
    }

    /**
     * GET / SET METHODS
     */

    public double x() {
        return x;
    }

    public WaypointObject x(double x) {
        this.x = x;
        return this;
    }

    public double y() {
        return y;
    }

    public WaypointObject y(double y) {
        this.y = y;
        return this;
    }

    public double orientation() {
        return orientation;
    }

    public WaypointObject orientation(double orientation) {
        this.orientation = orientation;
        return this;
    }

    public WaypointObject worldTime(long worldTime) {
        this.worldTime = worldTime;
        return this;
    }

    public long worldTime() {
        return worldTime;
    }

    /**
     * TRANSFORM METHODS
     */

    public JsonObject toJsonObject() {
        JsonObject waypoint = new JsonObject();
        JsonArray waypoint_info = new JsonArray();

        waypoint_info.add(new JsonPrimitive(new Double(x)));
        waypoint_info.add(new JsonPrimitive(new Double(y)));
        waypoint_info.add(new JsonPrimitive(new Double(orientation)));

        if (worldTime == -1) waypoint_info.add(new JsonNull());
        else {
            waypoint_info.add(new JsonPrimitive(worldTime));
        }

        waypoint.add("waypoint", waypoint_info);
        return waypoint;
    }
}
