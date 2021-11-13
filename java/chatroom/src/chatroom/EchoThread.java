package chatroom;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class EchoThread extends Thread {
	private Socket mySocket = null;

	public EchoThread(Socket socket) {       // constructor method
		mySocket = socket;
	}
	public void run() {
		try {
			PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
			Scanner in = new Scanner(mySocket.getInputStream());
			String inputLine;
			while (true) {
				inputLine = in.nextLine();      
				if (inputLine.equalsIgnoreCase("Bye")) {
					out.println("Closing connection");
					break;      
				} else {
					out.println(inputLine);
				}
			}
			out.close();
			in.close();
			mySocket.close(); 
		} catch (Exception e) {
			System.err.println("Connection reset");   // bad error (client died?)
		}
	}
}
