package com.visionire.downtown.output;

import akka.actor.TypedActor;
import com.visionire.downtown.net.SocketConnection;
import com.visionire.downtown.util.Log;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author nickbabenko
 */
public class OutputQueueImpl extends TypedActor implements OutputQueue {
    private ArrayList<Response> queue = new ArrayList<Response>();
    private PrintStream stream;
    private SocketConnection connection;

    private boolean processing = false;

    @Override
    public void connection(SocketConnection connection) {
        this.connection = connection;
    }

    @Override
    public void stream(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void add(Response response) {
        queue.add(response);

        if (processing == false) {
            process();
        }
    }

    private void process() {
        processing = true;

        while ((queue.size() > 0)) {
            
            Response current = queue.get(0);
            queue.remove(current);

            String string = current.toJSON();
            Log.out(Log.INFO, "Sending: " + string);
            stream.println(string);
        }

        processing = false;
    }
}