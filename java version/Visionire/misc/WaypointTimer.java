package Visionire.misc;


import Visionire.Framework.Objects.PersonObject;
import Visionire.Framework.Objects.WaypointObject;

import java.util.TimerTask;

/**
 *
 * @author nickbabenko
 */
public class WaypointTimer extends TimerTask {
    protected PersonObject person;
    protected WaypointObject waypoint;

    public WaypointTimer(PersonObject person, WaypointObject waypoint) {
        this.person = person;
	this.waypoint = waypoint;
    }

    public WaypointObject waypoint() {
        return waypoint;
    }

    @Override
    public void run() {
        person.path().add(waypoint);
        person.waypoint_timer(null);
    }
}
