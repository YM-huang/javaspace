package eigth_experience;
import java.util.*;

class RunnableExample1 implements Runnable{ 
	ArrayList<Integer> list = new ArrayList<Integer>();
	public void run(){ 
//	      while(this.list.size()<10000) {//持续卖票，一直到剩余票数为0；	                 
//	    	  this.list.add(1);
//	      }
		synchronized(this) {
			for(int i = 0; i < 10000 ; i++)
			{
				list.add(1);
//			System.out.println("大小-->"+(this.list.size()));
			}
			System.out.println(list.size());
		}
	} 
}
	
//class RunnableExample1example extends RunnableExample1 {
////	int ticket = 10;
//	private ArrayList<Integer> list = new ArrayList<Integer>();
//    //覆写RunnableExample1类的run()方法；
//    public void run() {
//    	//持续卖票，一直到剩余票数为0；
//    	while(this.list.size()<10000) { 
////    		System.out.println(this.getName()+"卖票-->"+(this.ticket--));
//    		this.list.add(1);
//    		System.out.println("大小-->"+(this.list.size()));
//    	}
////    	System.out.println(this.list.size());
//    }
//}

public class thread{
    public static void main(String args[]) {
    	RunnableExample1 r1= new RunnableExample1();//创建线程类
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1); 
		Thread t3 = new Thread(r1); 
		t1.setName("窗口1"); //给线程命名
		t2.setName("窗口2");
		t3.setName("窗口3");
		t1.start(); //线程运行
		t2.start(); 
		t3.start(); 
//		System.out.println(r1.list.size());
//		System.out.println(r2.list.size());
//		System.out.println(r3.list.size());
    }
}