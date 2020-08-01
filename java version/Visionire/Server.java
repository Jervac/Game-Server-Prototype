package Visionire;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.atomic.AtomicBoolean;

import Visionire.Framework.Network.ClientHandler;


// TODO (Jay): Remove this if not needed again. Interfaces are meant for
// TODO (Jay): being used multiple times
interface Implementation {

    /**
     * Starts the server application (if it has stopped running).
     * @throws Throwable if an error has encountered.
     */
    void construct() throws Throwable;

    /**
     * Stops the server (if started).
     */
    void deconstruct();

}

public class Server implements Implementation, Runnable {

    private final AtomicBoolean started;
    private ServerSocket serverSocket;
    private Thread thread;
    
    private boolean connected = false;
	private boolean running = false;
	private int port;
	
	
    public Server (int port) {
        this.started = new AtomicBoolean();
        this.port = port;
    }

    public void construct() {
    	try {
	    	serverSocket = new ServerSocket(port);
			thread = new Thread(this);
			thread.start();
			running = true;
	        run();
    	} catch (IOException e) {}
    }
    
    public void run() {
    	while (running) {
			try {
				Socket socket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(socket);
				clientHandler.launch();
				Utils.printWithTime("A new connection was made.");
			} catch (IOException e) {
				Utils.printWithTime("Exception occured while accepting a new connection:");
				e.printStackTrace();
			}
		}
    }

    @Override
    public void deconstruct () {
		try {
	        if (!started.getAndSet(false)) return;
	        connected = false;
	        running = false;
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to close Server! deconstruct()");
		}
    }

    public boolean initialise () {
        if (started.getAndSet(true)) return false;
        return true;
    }


    public boolean isConnected () {
        return connected;
    }
}