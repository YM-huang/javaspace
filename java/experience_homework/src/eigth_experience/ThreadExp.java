package eigth_experience;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
/**
 * @author Guodao Sun
 * ≤‚ ‘synchronized£¨“‘º∞vector
 */
public class ThreadExp implements Runnable{

	//ArrayList<Integer> list = new ArrayList<Integer>();
	//Vector<Integer> list = new Vector<Integer>();

	List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

	
	@Override
	public void run() {
		//synchronized (this) {
		   for(int i = 0; i < 10000; i++) {
			   list.add(i); 
		    } 
		    System.out.println("in run: "  +  list.size());
		//}
	}
	
	public static void main(String[] args) throws Exception {
		ThreadExp run = new ThreadExp();
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		Thread t3 = new Thread(run);

		t1.start();
		t2.start();
		t3.start();
		
		
		System.out.println("In main: " + run.list.size());
	}
}

/*
Exception: You are adding elements too fast 
so ArrayList#add() -> grow() -> newCapacity() can't calculate the correct capacity 
to allocate the memory for all of the elements coming in.
 */
