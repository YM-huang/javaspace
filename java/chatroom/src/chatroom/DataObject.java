package chatroom;

import java.io.*;
import java.util.*;

public class DataObject implements Serializable{
	private String message;
	private String name;
	private boolean connect;
	
	DataObject(){
		
		name = "";
		message = "";
		connect= false;
		
		
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String in){
		message = in;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String in){
		name = in;
	}
	
	public boolean getConnect(){
		return connect;
	}

	public void setConnect(boolean c){
		connect = c;
	}
}

