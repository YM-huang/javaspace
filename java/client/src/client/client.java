package client;

import java.io.*;
import java.net.*;
import java.util.*;

import chatui.UI;

public class client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s=new Socket("10.136.2.15",4444);  
		DataInputStream din=new DataInputStream(s.getInputStream());  
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
    
		UI mainGUI = new UI(s);
		String str="",str2="";  
		while(!str.equals("stop")){  
//			str=br.readLine();  
//			dout.writeUTF(str);  
//			dout.flush();  
			str2=din.readUTF();
			mainGUI.chatBox.append("<Server>:  " + str2+ "\n");
//			System.out.println("Server says: "+str2);  
		}  
    
		dout.close();  
		s.close();  
	}
}
