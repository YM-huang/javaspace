package second_homework;

import java.util.Random;


public class s1 {
	private static s1 JJ1 = new s1();
	private static s1 JJ2 = new s1();
	private static s1 JJ3 = new s1();
	private int n;
	public static int t=-1;
	private s1() {};
	
	public static s1 getInstance() {
		t=(t+1)%3;
		if(t==0)
		{	
			return JJ1;
		}
		else if(t==1)
		{
			return JJ2;
		}
		else if(t==2)
		{
			return JJ3;
		}
		return JJ1;
	}
	
	public static s1 getRandomInstance() {
		Random r = new Random();
		int num = r.nextInt(3);
		if(num==0)
		{	
			return JJ1;
		}
		else if(num==1)
		{
			return JJ2;
		}
		else if(num==2)
		{
			return JJ3;
		}
		return JJ1;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	public static void main(String[] args) {
		s1 a = s1.getInstance();
		s1 b = s1.getInstance();
		s1 c = s1.getInstance();
		s1 d = s1.getInstance(); 
		a.setN(10);
		b.setN(20);
		c.setN(30);
		d.setN(40);
		System.out.println("不随机获得：");
		System.out.println(a.getN());
		System.out.println(b.getN());
		System.out.println(c.getN());
		System.out.println(d.getN());
//		System.out.println(a == b);
		System.out.println("随机获得：");
		s1 e = s1.getRandomInstance();
		System.out.println(e.getN());
		s1 f = s1.getRandomInstance();
		System.out.println(f.getN());
		s1 g = s1.getRandomInstance();
		System.out.println(g.getN());
	}

}
