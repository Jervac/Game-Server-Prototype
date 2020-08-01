package com.visionire.downtown.output;

import com.visionire.downtown.net.SocketConnection;
import java.io.PrintStream;

/**
 *
 * @author nickbabenko
 */
public interface OutputQueue {
    public void connection(SocketConnection connection);
    public void stream(PrintStream stream);
    public void add(Response response);
}