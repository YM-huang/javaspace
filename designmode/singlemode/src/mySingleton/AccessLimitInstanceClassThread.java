package mySingleton;

import java.util.Random;

public class AccessLimitInstanceClassThread extends Thread {
	String tn=new String();
	public AccessLimitInstanceClassThread(String n)
	{
		tn=n;		
	}
	
	@SuppressWarnings("unused")
	public void run(){
	
		LimitInstanceClass a=LimitInstanceClass.getInstance(); 
		a.writeAccessMessage(tn);
		final Random random = new Random();
		try {
			Thread.sleep((int)(Math.random()*5000));
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
			a.printAccessMessage();
			a.release();
	}
}
