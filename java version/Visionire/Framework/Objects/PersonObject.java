package Visionire.Framework.Objects;

import Visionire.misc.WaypointTimer;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import com.mongodb.DBObject;

import java.util.HashMap;
import java.util.Timer;

/**
 *
 * @author nickbabenko
 */
public class PersonObject {
  //////  public static void fromUserObject(UserObject user, SocketConnectionImpl connection) {
        HashMap<String,Object> person_query = new HashMap<String,Object>();
 //////////       person_query.put("user_id", user.recordID());

//////////        PersonObject person = fromDBObject(connection.database().find_one("persons", person_query)).user(user);
// //////       connection.person(person);
  /////  }

    public static PersonObject fromDBObject(DBObject data) {
        return new PersonObject(data);
    }

    protected DBObject record;
    protected int objectID;

    protected PathObject path;
    protected int spriteRI;
    protected UserObject user;

    protected WaypointTimer waypoint_timer;

    public PersonObject(DBObject data) {
        this.objectID = ((Double) data.get("objectid")).intValue();
        this.path = new PathObject();

        path.add(new WaypointObject());
    }

    /**
    * GET / SET METHODS
    */

    public int objectID() {
        return objectID;
    }

    public UserObject user() {
        return user;
    }

    public PersonObject path(PathObject path) {
        this.path = path;
        return this;
    }

    public PathObject path() {
        return path;
    }

    public PersonObject user(UserObject user) {
        this.user = user;
        return this;
    }

    public WaypointObject currentWaypoint() {
        return path.getCurrent();
    }

    public PersonObject waypoint_timer(WaypointTimer waypoint_timer) {
        this.waypoint_timer = waypoint_timer;
        return this;
    }

    public WaypointTimer waypoint_timer() {
        return waypoint_timer;
    }

    public void updateWaypoint(WaypointObject waypoint, long length) {
    ///////    waypoint_timer = new WaypointTimer(this, waypoint);
        new Timer().schedule(waypoint_timer, length);
    }

    /**
     * TRANSFORM METHODS
     */

    public JsonObject toJsonObject() {

        JsonObject person = new JsonObject();
        JsonArray person_data = new JsonArray();
        JsonArray latest_waypoint = new JsonArray();

        latest_waypoint.add(currentWaypoint().toJsonObject());

        person_data.add(latest_waypoint); // Path
        person_data.add(new JsonNull()); // spriteRI - current unsupported
        person_data.add(user.toJsonObject()); // User

        person.add("person", person_data);
        return person;

    }
}
