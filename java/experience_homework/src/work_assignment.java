import java.util.Scanner;

public class work_assignment {
	static int n;
	static int[][] p;
	static int[][] t;   //标记数组
	static int minP;
	static int tempMinP=0;
	public static void main(String[] args) {
		
		Scanner input= new Scanner(System.in);
		n=input.nextInt();
		p=new int[n][n];
		t=new int[n][n];
		
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < n; j++) {
				p[i][j]=input.nextInt();
				minP+=p[i][j];
			}
			
		}
		
		backTack(0);
		System.out.println(minP);
		input.close();
		
	}
	
	//	回溯法
	public static void backTack(int k){
		
		if(k==n){
			if(tempMinP<minP){
				minP=tempMinP;
			}
			return;
		}
		
		for (int i = 0; i < n; i++) {
			
			if(isok(k, i)){
				tempMinP+=p[k][i];
				t[k][i]=1;
				backTack(k+1);
				//若无，进行回溯
				tempMinP-=p[k][i];
				t[k][i]=0;
			}
			
		}
		
	}
	
	//剪枝函数
	public static boolean isok(int k,int i){
		
		for (int j = 0; j < n; j++) {
			
			if(t[k][j]==1){
				return false;
			}
			
		}
		
		for (int j = 0; j < n; j++) {
			
			if(t[j][i]==1){
				return false;
			}
			
		}
		
		return true;
		
	}
}
