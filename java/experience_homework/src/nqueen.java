

public class nqueen{
	private static final int N = 8;
	private int[] x = new int[N];
	
	int sum  = 0;

	// 回溯寻找
	void backTack(int n) {
		
		if(n == N) {
			for (int i = 0; i < x.length; i++) {
				System.out.print("x["+i+"] = " +x[i] + ",");
			}
			System.out.println();
			sum ++;
		}
		else {
			for (int i = 0; i < N; i++) {
				x[n] = i;
				if(isPlace(n)) {//如果第n行可以放，继续看n + 1行
					backTack(n + 1);
				}
			}
		}
		
	}

	// 在i行可否放置皇后   0 <= i <= n  剪枝函数
	private boolean isPlace(int i) {
		
		for (int j = 0; j < i; j++) {
			//斜行相交
			if(Math.abs(i - j) == Math.abs(x[i] - x[j])  || x[i] == x[j])  {
				return false;
			}
		}
		return true;
	}
 
	public static void main(String[] args) {
		nqueen quene = new nqueen();
		
		long startTime=System.nanoTime();
		quene.backTack(0);
		long endTime=System.nanoTime();
		System.out.println("程序运行时间："+(endTime-startTime)+"ns");
		
		System.out.println(quene.sum);
		
	}
		
}