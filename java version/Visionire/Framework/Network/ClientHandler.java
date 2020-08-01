package Visionire.Framework.Network;

import java.io.IOException;
import java.net.Socket;

import Visionire.Const;
import Visionire.Utils;

public class ClientHandler implements Runnable{

	private Socket socket;
	private GMLInputStream in;
	private GMLOutputStream out;
	private Thread thread;
	private boolean running = false;
	
	public ClientHandler(Socket socket) {
		thread = new Thread(this);
		this.socket = socket;
	}
	
	public void launch() throws IOException {
		in  = new GMLInputStream(socket.getInputStream());
		out = new GMLOutputStream(socket.getOutputStream());
		running = true;
		thread.start();
	}
	
	@Override
	public void run() {
		while (running) {
			try {
				int magicNumber = in.readS32();
				//if (magicNumber != Const.MAGIC_NUMBER) {
					//throw new Exception("Message did not start with the magic number, but: " + magicNumber + ".");
					System.out.println("Message did not start with the magic number, but: " + magicNumber + "....");
					System.out.println(magicNumber + "tint");
				//	System.out.println(in.readString());

				//}
				//int ass = in.readS32();
				System.out.println(magicNumber + "tint");
				short size = in.readS16();
				RequestHandler.handleRequest(size, in, out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Utils.printWithTime(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}