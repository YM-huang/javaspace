//Java中，如果我们创建一个类，想让这个类只有一个对象，那么我们可以
//
//　　　　1:把该类的构造方法设计为private
//
//　　　　2：在该类中定义一个static方法，在该方法中创建对象

package single;

public class single {
	private static final single JJ = new single();
	private int n;
	private single() {};
	
	public static single getInstance() {
		return JJ;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	public static void main(String[] args) {
		single a = single.getInstance();
		single b = single.getInstance();
		a.setN(10);
		b.setN(20);
		System.out.println(a == b);
	}

}
