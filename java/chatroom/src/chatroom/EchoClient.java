package chatroom;

import java.util.Scanner;
import java.io.*;
import java.net.*;
public class EchoClient {
	public static void main(String[] args) throws IOException { 
		Socket echoSocket = null;
		//PrintWriter socketOut = null;
		try {
			echoSocket = new Socket("192.168.43.164", 4444); // connect to self at port 4444
			System.out.println("Connected OK");
			Scanner socketIn = new Scanner(echoSocket.getInputStream());  // set up input from socket
        	PrintWriter socketOut = new PrintWriter(echoSocket.getOutputStream(), true);    // set up output to socket
        	Scanner kbdIn = new Scanner(System.in);     // Scanner to pick up keyboard input
        	String serverResp = "";
        	String userInput = kbdIn.nextLine();        // get input from the user

        	while (true) {
        		socketOut.println(userInput);                   // send user input to the socket
            	serverResp =  socketIn.nextLine();     // get the response from the socket
            	System.out.println("echoed back: " + serverResp);   // print it out
            	if (serverResp.equals("bye")) { break; } //break if we're done
            	userInput = kbdIn.nextLine();   // get next user input
        	}
        	socketOut.close();
        	kbdIn.close();
        	socketIn.close();
		}
		catch (ConnectException e) {
			System.err.println("Could not connect to host");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Couldn't get I/O for connection");
			System.exit(1);
		}
		echoSocket.close();
	}
}
