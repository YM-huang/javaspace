//Java�У�������Ǵ���һ���࣬���������ֻ��һ��������ô���ǿ���
//
//��������1:�Ѹ���Ĺ��췽�����Ϊprivate
//
//��������2���ڸ����ж���һ��static�������ڸ÷����д�������

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
