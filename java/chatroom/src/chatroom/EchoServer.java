package chatroom;

import java.net.ServerSocket;
import java.net.Socket;
public class EchoServer {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(4444);   // create server socket on this machine
		System.err.println("Started server listening on port 4444");
		while (true) {        // as each connection is made, pass it to a thread
			//new EchoThread(serverSocket.accept()).start();  // this is same as next 3 lines
			Socket x = serverSocket.accept();   // block until next connection
			EchoThread et = new EchoThread(x);
			et.start();  
			System.err.println("Accepted connection from client");
		}
	}
}
