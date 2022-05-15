package mySingleton;

public class UseLimitInstanceClass {
	public static void main(String args[]){   
		for(int i=0;i<10;i++){
			AccessLimitInstanceClassThread t=new AccessLimitInstanceClassThread("thread"+i);
		    t.start();
		}
	}
}
