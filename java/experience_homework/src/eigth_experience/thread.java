package eigth_experience;
import java.util.*;

class RunnableExample1 implements Runnable{ 
	ArrayList<Integer> list = new ArrayList<Integer>();
	public void run(){ 
//	      while(this.list.size()<10000) {//������Ʊ��һֱ��ʣ��Ʊ��Ϊ0��	                 
//	    	  this.list.add(1);
//	      }
		synchronized(this) {
			for(int i = 0; i < 10000 ; i++)
			{
				list.add(1);
//			System.out.println("��С-->"+(this.list.size()));
			}
			System.out.println(list.size());
		}
	} 
}
	
//class RunnableExample1example extends RunnableExample1 {
////	int ticket = 10;
//	private ArrayList<Integer> list = new ArrayList<Integer>();
//    //��дRunnableExample1���run()������
//    public void run() {
//    	//������Ʊ��һֱ��ʣ��Ʊ��Ϊ0��
//    	while(this.list.size()<10000) { 
////    		System.out.println(this.getName()+"��Ʊ-->"+(this.ticket--));
//    		this.list.add(1);
//    		System.out.println("��С-->"+(this.list.size()));
//    	}
////    	System.out.println(this.list.size());
//    }
//}

public class thread{
    public static void main(String args[]) {
    	RunnableExample1 r1= new RunnableExample1();//�����߳���
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1); 
		Thread t3 = new Thread(r1); 
		t1.setName("����1"); //���߳�����
		t2.setName("����2");
		t3.setName("����3");
		t1.start(); //�߳�����
		t2.start(); 
		t3.start(); 
//		System.out.println(r1.list.size());
//		System.out.println(r2.list.size());
//		System.out.println(r3.list.size());
    }
}