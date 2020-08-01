package Visionire.Framework.Network;

import java.io.IOException;

import Visionire.Const;
import Visionire.Utils;

public class RequestHandler {
	
	private static final short DEBUG = 0;
	private static final short PUT_NAME = 1;
	
	public static void handleRequest(short size, GMLInputStream in, GMLOutputStream out) throws IOException {
		short request = in.readS16();
		switch (request) {
			case DEBUG: handleDEBUG(in, out); break;
			case PUT_NAME: handlePUT_NAME(in, out); break;
			default: Utils.printWithTime("Recieved an unkown request.");
		}
		out.flush();
	}
	
	private static void prepareResponse(GMLOutputStream out, short size) throws IOException {
		out.writeS32(Const.MAGIC_NUMBER);
		out.writeS16(size);
	}
	
	private static void handleDEBUG(GMLInputStream in, GMLOutputStream out) throws IOException {
		String message = in.readString();
		Utils.printWithTime("Recieved a DEBUG request. Message: \"" + message + "\".");
		prepareResponse(out, (short)(20 + message.length()));
		out.writeS16(DEBUG);
		out.writeString("Server received: " + message);
	}
	
	private static void handlePUT_NAME(GMLInputStream in, GMLOutputStream out) throws IOException {
		Utils.printWithTime("Recieved a PUT_NAME request. Name: " + in.readString());
		prepareResponse(out, (short)5);
		out.writeS16(PUT_NAME);
		out.writeString("OK");
	}
}
