package Visionire.path;

import com.visionire.downtown.net.SocketConnection;
import com.visionire.downtown.obj.PathObject;
import com.visionire.downtown.obj.WaypointObject;
import com.visionire.downtown.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author nickbabenko
 * @author Jervac
 */
public class PathWalker {

    // TODO(Jervac): Make this associate with the current client so
    // TODO(Jervac): the pathwalker knows who's who
    private SocketConnection connection;
    private Timer timer;

    public PathWalker(SocketConnection connection, PathObject path) {
        this.connection = connection;

        // Associate this PathWalker with the connection.
        connection.pathWalker(this);

        // Set the path for this walk
        connection.person().path(path);

        // Begin the walk process
        nextPoint();
    }
    
    public void close () {
        // Terminates the PathWalker instance.
        if (timer != null) timer.cancel();
    }

    private void nextPoint() {

        WaypointObject current = connection.person().path().get(0);
        WaypointObject next = connection.person().path().get(1);

        (timer = new Timer()).schedule(new TimerTask() {

            @Override
            public void run() {
                connection.person().path().remove(0);

                // We have reached our destination.
                if (connection.person().path().length() == 1) return;
                nextPoint();
            }

        }, (next.worldTime() - current.worldTime()));

    }
}